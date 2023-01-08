package edu.sakarya.operatingsystemhw.enums;

public enum Colors {
   RESET("\033[0m"),
   BLACK("\033[0;30m"),// SİYAH
   RED("\033[0;31m"),  // KIRMIZI
   GREEN("\033[0;32m"),// YEŞİL
   YELLOW("\033[0;33m"), // SARI
   BLUE("\033[0;34m"), // MAVİ
   PURPLE("\033[0;35m"), // MOR
   CYAN("\033[0;36m"), // CYAN
   WHITE("\033[0;37m"),// BEYAZ

    // Kalın
   BLACK_BOLD("\033[1;30m"), // SİYAH
   RED_BOLD("\033[1;31m"), // KIRMIZI
   GREEN_BOLD("\033[1;32m"), // YEŞİL
   YELLOW_BOLD("\033[1;33m"),// SARI
   BLUE_BOLD("\033[1;34m"),// MAVİ
   PURPLE_BOLD("\033[1;35m"),// MOR
   CYAN_BOLD("\033[1;36m"),// CYAN
   WHITE_BOLD("\033[1;37m"), // BEYAZ

    // Altı çizgili
   BLACK_UNDERLINED("\033[4;30m"), // SİYAH
   RED_UNDERLINED("\033[4;31m"), // KIRMIZI
   GREEN_UNDERLINED("\033[4;32m"), // YEŞİL
   YELLOW_UNDERLINED("\033[4;33m"),// SARI
   BLUE_UNDERLINED("\033[4;34m"),// MAVİ
   PURPLE_UNDERLINED("\033[4;35m"),// MOR
   CYAN_UNDERLINED("\033[4;36m"),// CYAN
   WHITE_UNDERLINED("\033[4;37m"), // BEYAZ

   BLACK_BACKGROUND("\033[40m"),
   RED_BACKGROUND("\033[41m"),
   GREEN_BACKGROUND("\033[42m"),
   YELLOW_BACKGROUND("\033[43m"),
   BLUE_BACKGROUND("\033[44m"),
   PURPLE_BACKGROUND("\033[45m"),
   CYAN_BACKGROUND("\033[46m"),
   WHITE_BACKGROUND("\033[47m"),

   BLACK_BRIGHT("\033[0;90m"),
   RED_BRIGHT("\033[0;91m"),
   GREEN_BRIGHT("\033[0;92m"),
   YELLOW_BRIGHT("\033[0;93m"),
   BLUE_BRIGHT("\033[0;94m"),
   PURPLE_BRIGHT("\033[0;95m"),
   CYAN_BRIGHT("\033[0;96m"),
   WHITE_BRIGHT("\033[0;97m"),

   BLACK_BOLD_BRIGHT("\033[1;90m"),
   RED_BOLD_BRIGHT("\033[1;91m"),
   GREEN_BOLD_BRIGHT("\033[1;92m"),
   YELLOW_BOLD_BRIGHT("\033[1;93m"),
   BLUE_BOLD_BRIGHT("\033[1;94m"),
   PURPLE_BOLD_BRIGHT("\033[1;95m"),
   CYAN_BOLD_BRIGHT("\033[1;96m"),
   WHITE_BOLD_BRIGHT("\033[1;97m"),

   BLACK_BACKGROUND_BRIGHT("\033[0;100m"),
   RED_BACKGROUND_BRIGHT("\033[0;101m"),
   GREEN_BACKGROUND_BRIGHT("\033[0;102m"),
   YELLOW_BACKGROUND_BRIGHT("\033[0;103m"),
   BLUE_BACKGROUND_BRIGHT("\033[0;104m"),
   PURPLE_BACKGROUND_BRIGHT("\033[0;105m"),
   CYAN_BACKGROUND_BRIGHT("\033[0;106m"),
   WHITE_BACKGROUND_BRIGHT("\033[0;107m");

   private String ansiColor;

   private Colors(String ansiColor){
      this.ansiColor = ansiColor;
   }

   public String getAnsiColor(){
      return ansiColor;
   }
}
