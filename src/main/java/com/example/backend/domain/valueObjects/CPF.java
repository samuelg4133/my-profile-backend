package com.example.backend.domain.valueObjects;

public class CPF {
  private final String value;

  public String getValue() {
    return value;
  }

  private CPF(String value) {
    this.value = value;
  }

  public static CPF of(String value) {
    if (!isValid(value)) {
      throw new IllegalArgumentException("Invalid CPF format");
    }
    return new CPF(value);
  }

  public static String clean(String cpf) {
    cpf = cpf.replaceAll("\\D", "");

    if (cpf.length() > 11) {
      cpf = cpf.substring(0, cpf.length() - 1);
    }

    return cpf;
  }

  public static boolean isValid(String cpf) {
    cpf = clean(cpf);

    if (cpf.length() != 11 || cpf.matches(cpf.charAt(0) + "{11}")) {
      return false;
    }

    int sum = 0;
    for (int i = 0; i < 9; i++) {
      sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
    }
    int firstDigit = 11 - (sum % 11);
    if (firstDigit >= 10) {
      firstDigit = 0;
    }

    sum = 0;
    for (int i = 0; i < 10; i++) {
      sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
    }
    int secondDigit = 11 - (sum % 11);
    if (secondDigit >= 10) {
      secondDigit = 0;
    }

    return Character.getNumericValue(cpf.charAt(9)) == firstDigit &&
        Character.getNumericValue(cpf.charAt(10)) == secondDigit;
  }

  public static String toMask(String cpf) {
    String cleanCPF = clean(cpf);

    StringBuilder formattedCPF = new StringBuilder(cleanCPF);
    if (formattedCPF.length() >= 3) {
      formattedCPF.insert(3, '.');
    }
    if (formattedCPF.length() >= 7) {
      formattedCPF.insert(7, '.');
    }
    if (formattedCPF.length() >= 11) {
      formattedCPF.insert(11, '-');
    }

    return formattedCPF.toString();
  }

  @Override
  public String toString() {
    return value;
  }
}
