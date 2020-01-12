package com.pl.utility;

import javafx.scene.text.Font;

public class FontLoader {
	
	public static void LoadAll() {
        Font.loadFont(FontLoader.class.getResourceAsStream("/resources/font/Uniform-Rounded.ttf"), 14);
        Font.loadFont(FontLoader.class.getResourceAsStream("/resources/font/Uniform-Rounded-Bold.ttf"), 14);
	}
}
