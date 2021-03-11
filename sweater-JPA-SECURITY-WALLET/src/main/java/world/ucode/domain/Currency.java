package world.ucode.domain;

import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "currency")
public class Currency {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String curName;
    private String ticker;

//    @ManyToOne ToMany(mappedBy ="wallet_id", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinColumn(name = "wallet_id", nullable = false)
//@JoinColumn(name = "wallet_id", nullable = false)
   //private Wallet wallet;

    public Currency(){ }

    public Currency(String name, String ticker) {
        this.curName = name;
        this.ticker = ticker;
    }

   // public Integer getId() {
    //    return id;
    //}

    public String getCurName() {
        return curName;
    }

    public String getTicker() {
        return ticker;
    }

    public void setId(Integer id) {
        id = id;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

//    public Wallet getWallet() {
//        return wallet;
//    }
//
//    public void setWallet(Wallet wallet) {
//        this.wallet = wallet;
//    }
}

