package org.uniupo.it;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class GettonieraService {

    private final MqttClient mqttClient;
    private double creditoInserito;

    public GettonieraService(String machineId,MqttClient mqttClient) throws MqttException {
        this.mqttClient = mqttClient;
        this.creditoInserito = 0.0;
        String baseTopic = "macchina/" + machineId + "/gettoniera";
        this.mqttClient.subscribe(baseTopic + "/#", this::messageArrived);
    }

    private void messageArrived(String topic, MqttMessage mqttMessage) {
        if (topic.endsWith("/inserisci")) {
            double importo = Double.parseDouble(new String(mqttMessage.getPayload()));
            inserisciMoneta(importo);
        }
        if (topic.endsWith("/restituisci")) {
            restituisciCredito();
        }
    }

    private void inserisciMoneta(double importo) {
        creditoInserito += importo;
        System.out.println("Moneta inserita: " + importo + ". Credito totale: " + creditoInserito);
    }

    private void restituisciCredito() {
        System.out.println("Restituzione credito: " + creditoInserito);
        creditoInserito = 0.0;
    }
}
