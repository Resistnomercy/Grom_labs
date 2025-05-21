from confluent_kafka import Consumer
import json
from collections import defaultdict
import sys

def main():
    conf = {
        'bootstrap.servers': 'localhost:9092',
        'group.id': 'user_info_group',
        'auto.offset.reset': 'earliest'
    }

    consumer = Consumer(conf)
    consumer.subscribe(['user_info'])  # Название топика можно задать в producer

    stats = {
        'total': 0,
        'occupations': defaultdict(int),
        'ages': []
    }

    try:
        print("Kafka Consumer запущен. Ожидание сообщений с именем, возрастом и занятием...")
        print("Нажмите Ctrl+C для выхода")

        while True:
            msg = consumer.poll(1.0)

            if msg is None:
                continue
            if msg.error():
                print(f"Ошибка: {msg.error()}")
                continue

            try:
                data = json.loads(msg.value())
                name = data.get('name', 'unknown')
                age = data.get('age')
                occupation = data.get('occupation', 'unknown')

                # Обновляем статистику
                stats['total'] += 1
                stats['occupations'][occupation] += 1
                if isinstance(age, int):
                    stats['ages'].append(age)

                # Выводим сообщение
                print("\n--- Новое сообщение ---")
                print(f"Топик: {msg.topic()}")
                print(f"Партиция: {msg.partition()}")
                print(f"Ключ: {msg.key()}")
                print("Данные:")
                print(json.dumps(data, indent=2, ensure_ascii=False))

                # Выводим статистику
                print("\nТекущая статистика:")
                print(f"Всего пользователей: {stats['total']}")
                print("Популярные занятия:")
                for occupation, count in sorted(stats['occupations'].items(), key=lambda x: x[1], reverse=True)[:5]:
                    print(f"  {occupation}: {count}")
                if stats['ages']:
                    avg_age = sum(stats['ages']) / len(stats['ages'])
                    print(f"Средний возраст: {avg_age:.1f}")

            except json.JSONDecodeError:
                print(f"Получено некорректное сообщение: {msg.value()}")

    except KeyboardInterrupt:
        print("\nЗавершение работы консьюмера...")
    finally:
        consumer.close()

if __name__ == '__main__':
    main()