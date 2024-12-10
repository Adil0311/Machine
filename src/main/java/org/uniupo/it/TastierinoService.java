package org.uniupo.it;

import org.eclipse.paho.client.mqttv3.*;

public class TastierinoService {

    private final MqttClient mqttClient;
    private final String machineId;



    public TastierinoService(String machineId, MqttClient mqttClient) throws MqttException {
        this.machineId = machineId;
        String baseTopic = "macchina/" + machineId + "/tastierino";
        this.mqttClient = mqttClient;
        this.mqttClient.subscribe(baseTopic + "/#", this::messageArrived);
    }

    private void messageArrived(String topic, MqttMessage mqttMessage) {
        String payload = new String(mqttMessage.getPayload());
        System.out.println("Messaggio ricevuto: " + payload);
    }

    public void publishMessage(String topic, String message) {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        try {
            mqttClient.publish(topic, mqttMessage);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publishSelection(String code){
        String topic = "macchina/" +machineId + "/gestoreConsumabili/verificaConsumabili";
        MqttMessage message = new MqttMessage(code.getBytes());
        try {
            mqttClient.publish(topic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
