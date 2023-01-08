package edu.sakarya.operatingsystemhw.enums;

public enum Colors {
  RESET("\033[0m"), BLACK("\033[0;30m"), // SÄ°YAH
  RED("\033[0;31m"), // KIRMIZI
  GREEN("\033[0;32m"), // YEÅ�Ä°L
  YELLOW("\033[0;33m"), // SARI
  BLUE("\033[0;34m"), // MAVÄ°
  PURPLE("\033[0;35m"), // MOR
  CYAN("\033[0;36m"), // CYAN
  WHITE("\033[0;37m"), // BEYAZ

  BLACK_BACKGROUND("\033[40m"), RED_BACKGROUND("\033[41m"), GREEN_BACKGROUND(
      "\033[42m"), YELLOW_BACKGROUND("\033[43m"), BLUE_BACKGROUND(
          "\033[44m"), PURPLE_BACKGROUND("\033[45m"), CYAN_BACKGROUND("\033[46m"),

  BLACK_BRIGHT("\033[0;90m"), RED_BRIGHT("\033[0;91m"), GREEN_BRIGHT("\033[0;92m"), YELLOW_BRIGHT(
      "\033[0;93m"), BLUE_BRIGHT("\033[0;94m"), PURPLE_BRIGHT(
          "\033[0;95m"), CYAN_BRIGHT("\033[0;96m"), WHITE_BRIGHT("\033[0;97m"),

  BLACK_BOLD_BRIGHT("\033[1;90m"), RED_BOLD_BRIGHT("\033[1;91m"), GREEN_BOLD_BRIGHT(
      "\033[1;92m"), YELLOW_BOLD_BRIGHT("\033[1;93m"), BLUE_BOLD_BRIGHT(
          "\033[1;94m"), PURPLE_BOLD_BRIGHT(
              "\033[1;95m"), CYAN_BOLD_BRIGHT("\033[1;96m"), WHITE_BOLD_BRIGHT("\033[1;97m"),

  BLACK_BACKGROUND_BRIGHT("\033[0;100m"), RED_BACKGROUND_BRIGHT(
      "\033[0;101m"), GREEN_BACKGROUND_BRIGHT("\033[0;102m"), YELLOW_BACKGROUND_BRIGHT(
          "\033[0;103m"), BLUE_BACKGROUND_BRIGHT(
              "\033[0;104m"), PURPLE_BACKGROUND_BRIGHT("\033[0;105m");

  private String ansiColor;

  private Colors(String ansiColor) {
    this.ansiColor = ansiColor;
  }

  public String getAnsiColor() {
    return ansiColor;
  }
}
