/**********************************************************************
 *
 * This file is part of HBCI4Java.
 * Copyright (c) Olaf Willuhn
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 **********************************************************************/

package org.kapott.hbci.passport;

import org.kapott.hbci.manager.HBCIUtils;

/**
 * Implementierung eines PIN/TAN-Passport, welcher keine Daten im Dateisystem ablegt
 * sondern alle Daten im Speicher haelt.
 */
public class HBCIPassportPinTanMemory extends HBCIPassportPinTan
{

  /**
   * ct.
   * @param init Generische Init-Daten.
   */
  public HBCIPassportPinTanMemory(Object init)
  {
    super(init);
  }

  /**
   * @see org.kapott.hbci.passport.HBCIPassportPinTan#create()
   */
  @Override
  protected void create()
  {
    // Überschrieben, um das Einlesen der Passport-Datei zu überspringen.

    // Trotzdem benötigen wir hier den Aufruf von getCurrentTANMethod(), die ein passendes TAN-Verfahren ermittelt oder den Nutzer zur Eingabe auffordert.
    // Ansonsten wird tanMethod nicht gesetzt und die folgende Authentifizierung schlägt fehl.

    try
    {
      final String s = this.getCurrentTANMethod(false);
      HBCIUtils.log("saving current tan method: " + s, HBCIUtils.LOG_DEBUG);
      this.setCurrentTANMethod(s);
    }
    catch (Exception e)
    {
      // Nur zur Sicherheit. In der obigen Funktion werden u.U. eine Menge Sachen losgetreten.
      // Wenn da irgendwas schief laeuft, soll deswegen nicht gleich das Speichern der Config
      // scheitern. Im Zweifel speichern wir dann halt das ausgewaehlte Verfahren erstmal nicht
      // und der User muss es beim naechsten Mal neu waehlen
      HBCIUtils.log("could not determine current tan methode, skipping: " + e.getMessage(), HBCIUtils.LOG_DEBUG);
      HBCIUtils.log(e, HBCIUtils.LOG_DEBUG2);
    }
  }

  /**
   * @see org.kapott.hbci.passport.HBCIPassportPinTan#read()
   */
  @Override
  protected void read()
  {
    // Überschrieben, um das Einlesen der Passport-Datei zu überspringen.
    create();
  }

  /**
   * @see org.kapott.hbci.passport.HBCIPassportPinTan#saveChanges()
   */
  @Override
  public void saveChanges()
  {
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return this.getFileName();
  }
}


