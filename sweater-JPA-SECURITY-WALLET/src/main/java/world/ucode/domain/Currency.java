package world.ucode.domain;

import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;

    private String name;
    private String ticker;

    public Currency(){ }

    public Currency(String name, String ticker) {
        this.name = name;
        this.ticker = ticker;
    }

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}

