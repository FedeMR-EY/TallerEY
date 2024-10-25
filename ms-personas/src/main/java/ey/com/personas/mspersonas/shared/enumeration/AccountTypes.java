package ey.com.personas.mspersonas.shared.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountTypes {
  NO_ELIGIBLE(null, null, null), // Caso donde no se puede asignar ninguna cuenta
  BASIC(1.5, false, 0.0), // Cuenta PESOS $
  BASIC_PLUS_CARD(1.0, false, 446000.0), // Cuenta Dólar y pesos + Tarjeta basic
  PREMIUM_GOLD(0.5, false, 827000.0), // Cuenta Dólar y pesos + Tarjeta Gold
  PREMIUM_BLACK(0.1, false, 999000.0); // Cuenta Dólar y pesos + Tarjeta Black

  private final Double verazScore;
  private final Boolean worldSysFlag; // true si la persona es un riesgo (terrorista)
  private final Double sueldoBrutoMin;

  // Método para obtener el tipo de cuenta según los datos de la persona
  public static AccountTypes obtenerCuenta(
      Boolean renaperValid, Double verazScore, Boolean worldSysFlag, Double sueldoBruto) {
    // Si la validación de Renaper es falsa, no se puede crear ninguna cuenta
    if (Boolean.FALSE.equals(renaperValid)) {
      return NO_ELIGIBLE;
    }

    // Recorrer los tipos de cuentas para encontrar la más adecuada
    for (AccountTypes tipo : AccountTypes.values()) {
      if (tipo != NO_ELIGIBLE
          && verazScore >= tipo.getVerazScore()
          && !worldSysFlag.equals(true)
          && sueldoBruto >= tipo.getSueldoBrutoMin()) {
        return tipo;
      }
    }

    // Si no cumple con ninguna condición, retorna NO_ELIGIBLE
    return NO_ELIGIBLE;
  }
}
