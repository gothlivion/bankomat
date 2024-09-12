import java.util.Scanner;

public class Bankomat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String correctPassword = "0000"; // Korrektes password
        int versuch = 3; // Anzahl versuche
        double eurokontostand = 1000.0; // Aktueller Kontostand
        double dollarkontostand = 0;
        double rubelkontostand = 0;
        boolean zugangGewaehrt = false; 

        while (versuch > 0) {
            System.out.print("Bitte Passwort eingeben: ");
            String password = scanner.nextLine();
            if (password.equals(correctPassword)) {
                zugangGewaehrt = true;
                break;
            } else {
                versuch--;
                System.out.println("Falsches Passwort. Verbleibende Versuche: " + versuch);
            }
        }

        if (!zugangGewaehrt) {
            System.out.println("Error: Zu viele falsche Versuche. Zugriff verweigert.");
            scanner.close();
            return;
        }
        while (true) { // Optionen im Bankomat
            System.out.println("Willkommen! Ihr aktueller Kontostand ist: " + eurokontostand + " Euro " + dollarkontostand +" Dollars und " + rubelkontostand + " Rubel.");
            System.out.println("Was möchten Sie tun?");
            System.out.println("1. Geld einzahlen");
            System.out.println("2. Geld überweisen");
            System.out.println("3. Geld abheben");
            System.out.println("4. Kontostandabfrage");
            System.out.println("5. PIN ändern");
            System.out.println("6. Währung wechseln");
            System.out.println("7. Abmelden");
        

            int wahl = scanner.nextInt();
            scanner.nextLine();

            switch (wahl) {
                case 1: // Geld einzahlen
                
                    System.out.print("Wie viel Geld möchten Sie einzahlen? ");
                    double einzahlung = scanner.nextDouble();
                    scanner.nextLine(); 

                    if (einzahlung > 0) {
                        eurokontostand += einzahlung;
                        System.out.println("Einzahlung erfolgreich! Ihr neuer Kontostand ist: " + eurokontostand + " Euro");
                    } else {
                        System.out.println("Ungültiger Betrag. Einzahlung fehlgeschlagen.");
                    }
                    break;

                case 2: // Geld überweisen
                    
                    System.out.print("Wie viel Geld möchten Sie überweisen? ");
                    double ueberweisung = scanner.nextDouble();
                    scanner.nextLine(); 

                    if (ueberweisung > 0 && ueberweisung <= eurokontostand) {
                        eurokontostand -= ueberweisung;
                        System.out.println("Überweisung erfolgreich! Ihr neuer Kontostand ist: " + eurokontostand + " Euro");
                    } else if (ueberweisung > eurokontostand) {
                        System.out.println("Fehler: Nicht genügend Guthaben. Überweisung fehlgeschlagen.");
                    } else {
                        System.out.println("Ungültiger Betrag. Überweisung fehlgeschlagen.");
                    }
                    break;

                case 3: // Geld abheben

                    System.out.println("Wie viel Geld möchten Sie abheben? ");
                    double abheben = scanner.nextDouble();
                    scanner.nextLine();

                    if ( abheben <= eurokontostand) {
                        eurokontostand -= abheben;
                        System.out.println("Abheben von " + abheben + " Euro war erfolgreich! Ihr neuer Kontostand ist: " + eurokontostand + " Euro");
                    }  else if (abheben > eurokontostand) {
                        System.out.println("Fehler: Nicht genügend Guthaben. Ihr Kontostand reicht nicht aus.");
                    } else {
                        System.out.println("Ungültige eingabe. Geldabhebung fehlgeschlagen.");
                    }
                    break;

                case 4: // Kontostandabfrage
                    System.out.println("Ihr Kontostand beträgt " + eurokontostand + " Euro");
                    break;

                case 5: // PIN ändern
                    System.out.println("Möchten Sie ihren PIN jetzt ändern?");
                    System.out.println("1. Ja");
                    System.out.println("2. Nein");
                    int pinWahl = scanner.nextInt();
                    scanner.nextLine();

                    if (pinWahl == 1) {
                        System.out.println("Geben Sie Ihren aktuellen PIN ein: ");
                        String aktuellerPin = scanner.nextLine();
                        if (aktuellerPin.equals(correctPassword)) {
                            System.out.println("Geben Sie Ihren neuen PIN ein: ");
                            String neuerPin = scanner.nextLine();
                            System.out.println("Bestätigen Sie Ihren neuen PIN: ");
                            String bestaetigungPin = scanner.nextLine();

                            if (neuerPin.equals(bestaetigungPin) && !neuerPin.isEmpty()) {
                                correctPassword = neuerPin;
                                System.out.println("PIN erfolgreich geändert!");
                            } else {
                                System.out.println("Fehler: PINs stimmen nicht überein oder sind ungültig!");
                            }
                        } else {
                            System.out.println("Fehler: Falscher PIN.");
                        }
                    }   else {
                        System.out.println("PIN-Änderung abgebrochen.");
                    }
                    break;

                case 6: // Währung wechseln
                    System.out.println("Welche Währung möchten Sie wechseln?");
                    System.out.println("1. Euro");
                    System.out.println("2. Dollar");
                    System.out.println("3. Rubel");
                    int währungswahl = scanner.nextInt();
                    scanner.nextLine();

                    if (währungswahl == 1) { // Euro tauschen
                        System.out.println("Zu welcher Währung möchten Sie wechseln?");
                        System.out.println("1. Dollar");
                        System.out.println("2. Rubel");
                        int währungswahl1 = scanner.nextInt();
                        scanner.nextLine();

                        if (währungswahl1 == 1) {
                            System.out.println("Wie viel möchten Sie wechseln?: ");
                            double wechseln = scanner.nextDouble();
                            scanner.nextLine();

                            if ( wechseln <= eurokontostand) {
                                eurokontostand -= wechseln;
                                dollarkontostand += wechseln * 1.1;
                                System.out.println("Sie haben " + wechseln + " Euro in " + wechseln * 1.1 + " Dollars umgetauscht. Ihr neuer Kontostand beträgt " + eurokontostand + " Euro und " + dollarkontostand + " Dollars");
                            }  else if (wechseln > eurokontostand) {
                                System.out.println("Fehler: Nicht genügend Guthaben. Ihr Kontostand reicht nicht aus.");
                            } else {
                                System.out.println("Ungültige eingabe. Geldabhebung fehlgeschlagen.");
                            }
                            break;
                        }

                        if (währungswahl1 == 2) {
                            System.out.println("Wie viel möchten Sie wechseln?: ");
                            double wechseln = scanner.nextDouble();
                            scanner.nextLine();
        
                            if ( wechseln <= eurokontostand) {
                                eurokontostand -= wechseln;
                                rubelkontostand += wechseln * 100;
                                System.out.println("Sie haben " + wechseln + " Euro in " + wechseln * 100 + " Rubels umgetauscht. Ihr neuer Kontostand beträgt " + eurokontostand + " Euro und " + rubelkontostand + " Rubel");
                            }  else if (wechseln > eurokontostand) {
                                        System.out.println("Fehler: Nicht genügend Guthaben. Ihr Kontostand reicht nicht aus.");
                            } else {
                                        System.out.println("Ungültige eingabe. Geldabhebung fehlgeschlagen.");
                            }
                            break;
                        }
                    }

                    if (währungswahl == 2) { // Dollar tauschen
                        System.out.println("Zu welcher Währung möchten Sie wechseln?");
                        System.out.println("1. Euro");
                        System.out.println("2. Rubel");
                        int währungswahl2 = scanner.nextInt();
                        scanner.nextLine();

                        if (währungswahl2 == 1) {
                            System.out.println("Wie viel möchten Sie wechseln?");
                            double wechseln = scanner.nextDouble();
                            scanner.nextLine();

                            if ( wechseln <= dollarkontostand) {
                                dollarkontostand -= wechseln;
                                eurokontostand += wechseln * 0.91;
                                System.out.println("Sie haben " + wechseln + " Dollars in " + wechseln * 0.91 + " Euro umgetauscht. Ihr neuer Kontostand beträgt " + eurokontostand + " Euro und " + dollarkontostand + " Dollars");
                            }  else if (wechseln > dollarkontostand) {
                                System.out.println("Fehler: Nicht genügend Guthaben. Ihr Kontostand reicht nicht aus.");
                            } else {
                                System.out.println("Ungültige eingabe. Geldabhebung fehlgeschlagen.");
                            }
                            break;
                        }
                        if (währungswahl2 == 2) {
                            System.out.println("Wie viel möchten Sie wechseln?");
                            double wechseln = scanner.nextDouble();
                            scanner.nextLine();

                            if ( wechseln <= dollarkontostand) {
                                dollarkontostand -= wechseln;
                                rubelkontostand += wechseln * 91;
                                System.out.println("Sie haben " + wechseln + " Dollars in " + wechseln * 91 + " Rubel umgetauscht. Ihr neuer Kontostand beträgt " + dollarkontostand + " Dollars und " + rubelkontostand + " Rubel");
                            }  else if (wechseln > dollarkontostand) {
                                System.out.println("Fehler: Nicht genügend Guthaben. Ihr Kontostand reicht nicht aus.");
                            } else {
                                System.out.println("Ungültige eingabe. Geldabhebung fehlgeschlagen.");
                            }
                            break;
                        }
                    }
                        
                    if (währungswahl == 3) { // Rubel tauschen
                        System.out.println("Zu welcher Währung möchten Sie wechseln?");
                        System.out.println("1. Euro");
                        System.out.println("2. Dollar");
                        int währungswahl3 = scanner.nextInt();
                        scanner.nextLine();  

                        if (währungswahl3 == 1) {
                            System.out.println("Wie viel möchten Sie wechseln?");
                            double wechseln = scanner.nextDouble();
                            scanner.nextLine();

                            if ( wechseln <= rubelkontostand) {
                                rubelkontostand -= wechseln;
                                eurokontostand += wechseln / 100;
                                System.out.println("Sie haben " + wechseln + " Rubel in " + wechseln / 100 + " Euro umgetauscht. Ihr neuer Kontostand beträgt " + rubelkontostand + " Rubel und " + eurokontostand + " Euro");
                            }  else if (wechseln > rubelkontostand) {
                                System.out.println("Fehler: Nicht genügend Guthaben. Ihr Kontostand reicht nicht aus.");
                            } else {
                                System.out.println("Ungültige eingabe. Geldabhebung fehlgeschlagen.");
                            }
                            break;
                        }

                        if (währungswahl3 == 2) {
                            System.out.println("Wie viel möchten Sie wechseln?");
                            double wechseln = scanner.nextDouble();
                            scanner.nextLine();

                            if ( wechseln <= rubelkontostand) {
                                rubelkontostand -= wechseln;
                                dollarkontostand += wechseln / 91;
                                System.out.println("Sie haben " + wechseln + " Rubel in " + wechseln / 91 + " Dollars umgetauscht. Ihr neuer Kontostand beträgt " + rubelkontostand + " Rubel und " + dollarkontostand + " Dollars");
                            }  else if (wechseln > rubelkontostand) {
                                System.out.println("Fehler: Nicht genügend Guthaben. Ihr Kontostand reicht nicht aus.");
                            } else {
                                System.out.println("Ungültige eingabe. Geldabhebung fehlgeschlagen.");
                            }
                            break;
                        }
                    }
                        
                case 7: // Abmeldung
                    
                    System.out.println("Sie haben sich erfolgreich abgemeldet. Auf Wiedersehen!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Ungültige Auswahl. Bitte versuchen Sie es erneut.");
                    break;
            }
        }
    }
}