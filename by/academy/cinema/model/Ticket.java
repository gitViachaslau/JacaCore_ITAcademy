/**
 * Класс описывавет билет
 *
 * id - номер id;
 * IdUser - ID пользователя купившего этот билет;
 * IdEvent - ID мероприятия для которого этот билет;
 * seat - номер места;
 * price - стоимость за которую был куплен билет;
 * purchase - флаг куплен ли билет. false - не куплен, true - куплен;
 */

package by.academy.cinema.model;

public class Ticket {

    private int id;

    private String IdUser;

    private int IdEvent;

    private int seat;

    private double price;

    private boolean purchase;

    public Ticket(int id, String idUser, int idEvent, int seat, double price, boolean purchase) {
        this.id = id;
        IdUser = idUser;
        IdEvent = idEvent;
        this.seat = seat;
        this.price = price;
        this.purchase = purchase;
    }

    public Ticket (String idUser, int idEvent, int seat, double price, boolean purchase) {
        IdUser = idUser;
        IdEvent = idEvent;
        this.seat = seat;
        this.price = price;
        this.purchase = purchase;
    }

//    public Ticket (int idEvent, int seat, boolean purchase) {
//        IdEvent = idEvent;
//        this.seat = seat;
//        this.purchase = purchase;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }

    public int getIdEvent() {
        return IdEvent;
    }

    public void setIdEvent(int idEvent) {
        IdEvent = idEvent;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPurchase() {
        return purchase;
    }

    public void setPurchase(boolean purchase) {
        this.purchase = purchase;
    }
}
