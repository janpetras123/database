package application;

import javafx.beans.property.*;


/**
 * 
 * Trieda Overview slúži na nastavenie, získanie alebo vrátenie konkrétnych údajov.
 *
 */


public class Overview {
	
	private IntegerProperty id;
	private StringProperty name;
	private StringProperty surname;
	private StringProperty team;
	private StringProperty country;
	private IntegerProperty races;

        public Overview(int id, String name, String surname, String team, String country, int races) {
        	this.id = new SimpleIntegerProperty(id);
            this.name = new SimpleStringProperty(name);
            this.surname = new SimpleStringProperty(surname);
            this.team = new SimpleStringProperty(team);
            this.country = new SimpleStringProperty(country);
            this.races = new SimpleIntegerProperty(races);
        }
        
        public int getId() {
            return id.get();
        }

        public IntegerProperty idProperty() {
            return id;
        }

        public void setid(int id) {
            this.id.set(id);
        }
        
        public String getName() {
            return name.get();
        }

        public StringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }
        
        public String getSurname() {
            return surname.get();
        }

        public StringProperty surnameProperty() {
            return surname;
        }

        public void setSurnamea(String surname) {
            this.surname.set(surname);
        }

        public String getTeam() {
            return team.get();
        }

        public StringProperty teamProperty() {
            return team;
        }

        public void setTeam(String team) {
            this.team.set(team);
        }
        
        public String getCountry() {
            return country.get();
        }

        public StringProperty countryProperty() {
            return country;
        }

        public void setCountry(String country) {
            this.country.set(country);
        }

        public int getRaces() {
            return races.get();
        }

        public IntegerProperty racesProperty() {
            return races;
        }

        public void setRaces(int races) {
            this.races.set(races);
        }
}

