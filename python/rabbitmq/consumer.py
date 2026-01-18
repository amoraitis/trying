import pika
import json
from dotenv import load_dotenv

load_dotenv()

import rabbitmqsettings

def main():
    # Connect to RabbitMQ
    connection = pika.BlockingConnection(pika.ConnectionParameters(host=rabbitmqsettings.host, port=rabbitmqsettings.port, credentials=pika.PlainCredentials(rabbitmqsettings.user, rabbitmqsettings.password)))
    channel = connection.channel()
    channel.exchange_declare(exchange=rabbitmqsettings.exchange, exchange_type='direct', durable=True)
    
    # Declare queue
    channel.queue_declare(queue=rabbitmqsettings.queue, durable=True)
    channel.queue_bind(exchange=rabbitmqsettings.exchange, queue=rabbitmqsettings.queue)
    
    # Set QoS
    channel.basic_qos(prefetch_count=4)
    
    # Define callback function
    def callback(ch, method, properties, body):
        try:
            message = json.loads(body)
            print(f"Received message: {message}")
            # Process message here
            ch.basic_ack(delivery_tag=method.delivery_tag)
        except Exception as e:
            print(f"Error processing message: {e}")
            ch.basic_nack(delivery_tag=method.delivery_tag, requeue=True)
    
    # Set up consumer
    channel.basic_consume(queue=rabbitmqsettings.queue, on_message_callback=callback)
    
    print(f"Waiting for messages on {rabbitmqsettings.queue}...")
    channel.start_consuming()

if __name__ == '__main__':
    main()