
# ����spring�Ľ�����ֲ���˴���

## ����ҵ���߼�

���д������û�ͬʱ�ύ����μӽ���γ̵ı�ʱ��ֱ�ӽ������ݿ�Ĳ������˷Ѻܶ�ʱ�䣬ϵͳ����Ϣ����Ҳ���ӵ�¡��������ǽ�������ҳ�桱�͡��������ݿ⡱���������������˷��룬�ô洢�����ݿ���������뷵�ؽ����첽���ϵͳ����ӦЧ�ʡ�

## ʵ���߼�

ʹ��kafka��Ϊ��Ϣ���м�������ύ�������û�����Ϣ��Ϊ��Ϣ�����ݷ�����information���topic��ȥ,���ؽ��棬��ʾ�û���Ϣ���ڱ��档
����˼�����information�����topic��������Ϣ�󣬽���Ϣ����֮��洢�����ݿ���ȥ��

ʵ�ִ��룺
��1������spring-kafka������pom.xml��

>        <dependency>
>            <groupId>org.apache.kafka</groupId>
>            <artifactId>kafka-streams</artifactId>
>        </dependency>
>        <dependency>
>            <groupId>org.springframework.kafka</groupId>
>            <artifactId>spring-kafka</artifactId>
>        </dependency>
��2������spring-kafka��application.properties��
>       #Kafka Topic 
>       message.topic.name=information
>       spring.kafka.bootstrap-servers=localhost:9092
>       #Unique String which identifies which consumer group this       consumer belongs to
>       spring.kafka.consumer.group-id=jcg-group
��3������Producer

![ͼƬ��](/picture/ͼƬ3.png)

��4������Consumer

![ͼƬ��](/picture/ͼƬ2.png)

��5��Post�����

![ͼƬ��](/picture/ͼƬ4.png)

��6�����͸�kafka��Ⱥ

![ͼƬ��](/picture/ͼƬ5.png)
