import os 

exchange = os.getenv("RABBITMQ_EXCHANGE")
queue = os.getenv("RABBITMQ_QUEUE")
host = os.getenv("RABBITMQ_HOST")
port = int(os.getenv("RABBITMQ_PORT", 5672))
user = os.getenv("RABBITMQ_USER")
password = os.getenv("RABBITMQ_PASSWORD")