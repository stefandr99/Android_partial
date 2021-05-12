package com.example.morsecode;

import java.util.HashMap;

public class Translator {
    public HashMap<Character, String> encoder;
    public HashMap<String, Character> decoder;

    public Translator() {
        encoder = new HashMap<>();
        decoder = new HashMap<>();
        initiateEncoder();
        initiateDecoder();
    }

    public void initiateEncoder() {
        encoder.put('a', ".-");
        encoder.put('A', ".-");
        encoder.put('b', "-...");
        encoder.put('B', "-...");
        encoder.put('c', "-.-.");
        encoder.put('C', "-.-.");
        encoder.put('d', "-..");
        encoder.put('D', "-..");
        encoder.put('e', ".");
        encoder.put('E', ".");
        encoder.put('f', "..-.");
        encoder.put('F', "..-.");
        encoder.put('g', "--.");
        encoder.put('G', "--.");
        encoder.put('h', "....");
        encoder.put('H', "....");
        encoder.put('i', "..");
        encoder.put('I', "..");
        encoder.put('j', ".---");
        encoder.put('J', ".---");
        encoder.put('k', "-.-");
        encoder.put('K', "-.-");
        encoder.put('l', ".-..");
        encoder.put('L', ".-..");
        encoder.put('m', "--");
        encoder.put('M', "--");
        encoder.put('n', "-.");
        encoder.put('N', "-.");
        encoder.put('o', "---");
        encoder.put('O', "---");
        encoder.put('p', ".--.");
        encoder.put('P', ".--.");
        encoder.put('q', "--.-");
        encoder.put('Q', "--.-");
        encoder.put('r', ".-.");
        encoder.put('R', ".-.");
        encoder.put('s', "...");
        encoder.put('S', "...");
        encoder.put('t', "-");
        encoder.put('T', "-");
        encoder.put('u', "..-");
        encoder.put('U', "..-");
        encoder.put('v', "...-");
        encoder.put('V', "...-");
        encoder.put('w', ".--");
        encoder.put('W', ".--");
        encoder.put('x', "-..-");
        encoder.put('X', "-..-");
        encoder.put('y', "-.--");
        encoder.put('Y', "-.--");
        encoder.put('z', "--..");
        encoder.put('Z', "--..");

        encoder.put('1', ".----");
        encoder.put('2', "..---");
        encoder.put('3', "...--");
        encoder.put('4', "....-");
        encoder.put('5', ".....");
        encoder.put('6', "-....");
        encoder.put('7', "--...");
        encoder.put('8', "---..");
        encoder.put('9', "----.");
        encoder.put('0', "-----");

        encoder.put('.', ".-.-.-");
        encoder.put(',', "--..--");
        encoder.put('?', "..--..");
        encoder.put('!', "..--.");
        encoder.put(':', "---...");
        encoder.put('"', ".-..-.");
        encoder.put('\'', ".----.");
        encoder.put('=', "-...-");

        encoder.put(' ', "|");
    }

    public void initiateDecoder() {
        decoder.put(".-", 'A');
        decoder.put("-...", 'B');
        decoder.put("-.-.", 'C');
        decoder.put("-..", 'D');
        decoder.put(".", 'E');
        decoder.put("..-.", 'F');
        decoder.put("--.", 'G');
        decoder.put("....", 'H');
        decoder.put("..", 'I');
        decoder.put(".---", 'J');
        decoder.put("-.-", 'K');
        decoder.put(".-..", 'L');
        decoder.put("--", 'M');
        decoder.put("-.", 'N');
        decoder.put("---", 'O');
        decoder.put(".--.", 'P');
        decoder.put("--.-", 'Q');
        decoder.put(".-.", 'R');
        decoder.put("...", 'S');
        decoder.put("-", 'T');
        decoder.put("..-", 'U');
        decoder.put("...-", 'V');
        decoder.put(".--", 'W');
        decoder.put("-..-", 'X');
        decoder.put("-.--", 'Y');
        decoder.put("--..", 'Z');

        decoder.put(".----", '1');
        decoder.put("..---", '2');
        decoder.put("...--", '3');
        decoder.put("....-", '4');
        decoder.put(".....", '5');
        decoder.put("-....", '6');
        decoder.put("--...", '7');
        decoder.put("---..", '8');
        decoder.put("----.", '9');
        decoder.put("-----", '0');

        decoder.put(".-.-.-", '.');
        decoder.put("--..--", ',');
        decoder.put("..--..", '?');
        decoder.put("..--.", '!');
        decoder.put("---...", ':');
        decoder.put(".-..-.", '"');
        decoder.put(".----.", '\'');
        decoder.put("-...-", '=');

        decoder.put("| ", ' ');
    }
}
