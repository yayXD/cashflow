package world.ucode.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String walletName;
    private String type;
    private String category;
    private String tag;
    private String date;
    private String description;

    public Transaction(){}

    public Transaction(String walletName, String type, String category, String tag, String date, String description) {
        this.walletName = walletName;
        this.type = type;
        this.category = category;
        this.tag = tag;
        this.date = date;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getWalletName() {
        return walletName;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public String getTag() {
        return tag;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
