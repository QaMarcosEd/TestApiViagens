package com.montanha.gerenciador.utils;

public class DetalhesViagem {
    public static String toJson(String acompanhante, String dataPartida, String dataRetorno, String localDeDestino, String regiao) {
        return "{\n" +
                "  \"acompanhante\": \"" + acompanhante + "\",\n" +
                "  \"dataPartida\": \"" + dataPartida + "\",\n" +
                "  \"dataRetorno\": \"" + dataRetorno + "\",\n" +
                "  \"localDeDestino\": \"" + localDeDestino + "\",\n" +
                "  \"regiao\": \"" + regiao + "\"\n" +
                "}";
    }
}
