from confluent_kafka import Producer
import json
from datetime import datetime

def delivery_report(err, msg):
    if err is not None:
        print(f'Ошибка доставки: {err}')
    else:
        print(f'Сообщение доставлено в [{msg.topic()}] (партиция: {msg.partition()})')

def main():
    conf = {'bootstrap.servers': 'localhost:9092'}
    producer = Producer(conf)

    print("Kafka Producer — отправка данных пользователя (имя, возраст, занятие)")
    print("Пример ввода: {\"name\": \"Иван\", \"age\": 30, \"occupation\": \"Программист\"}")
    print("Для выхода введите 'exit'")

    while True:
        try:
            user_input = input("> ")

            if user_input.lower() == 'exit':
                break

            # Парсинг JSON
            try:
                message = json.loads(user_input)
            except json.JSONDecodeError:
                print("❌ Ошибка: Невалидный JSON. Пример: {\"name\": \"Анна\", \"age\": 25, \"occupation\": \"Аналитик\"}")
                continue

            # Добавляем timestamp (опционально)
            if 'timestamp' not in message:
                message['timestamp'] = datetime.now().isoformat()

            name_key = message.get('name', 'unknown')

            # Отправка сообщения
            producer.produce(
                topic='user_info',
                key=str(name_key),
                value=json.dumps(message, ensure_ascii=False),
                callback=delivery_report
            )

            producer.flush()

        except KeyboardInterrupt:
            print("\nЗавершение работы Producer...")
            break
        except Exception as e:
            print(f"Ошибка: {e}")

if __name__ == '__main__':
    main()