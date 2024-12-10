package org.uniupo.it.service;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.uniupo.it.components.Screen;

public class FrontendService {

    final String baseTopic;
    String machineId;
    MqttClient mqttClient;

    private Screen screen;

    public FrontendService(String machineId, MqttClient mqttClient) throws MqttException {
        this.machineId = machineId;
        this.mqttClient = mqttClient;
        this.screen = new Screen();
        baseTopic = "macchina/" + machineId + "/frontend";
        this.mqttClient.subscribe(baseTopic + "/#", this::messageArrived);

    }

    private void messageArrived(String topic, MqttMessage mqttMessage) {
        if (topic.equals(baseTopic + "/screen/update")) {
            String message= new String(mqttMessage.getPayload());
            screen.updateScreen(message);
        }

    }
}
