package com.metal;

import com.metal.kamasutra.Kamasutra;

public class Main {

    public static void main(String[] args) {

        Kamasutra kamasutra = new Kamasutra();
        String text = "hello world";
        String resA = kamasutra.encDec(text);
        System.out.println(resA);
        String resB = kamasutra.encDec(resA);
        System.out.println(resB);

        try {
            kamasutra = new Kamasutra("qwertyuiopasdfghjklñzxcvbnmç");
            String textEncDec = "this is a test with a custom alphabet";
            System.out.printf("Using '%s' as alphabet.%n", kamasutra.getDefaultAlphabet());
            kamasutra.setUseUppercase(true);
            textEncDec = kamasutra.encDec(textEncDec);
            System.out.println(textEncDec);
            System.out.println(kamasutra.encDec(textEncDec));
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
