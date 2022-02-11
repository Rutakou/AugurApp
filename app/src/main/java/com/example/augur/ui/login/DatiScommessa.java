package com.example.augur.ui.login;

public class DatiScommessa{

    private String valorefuturo;
    private String quantitascommessa;
    private String indirizzoportafoglio;

    public DatiScommessa(String valorefuturo, String quantitaScommessa, String indirizzoportafoglio){
        setValoreFuturo(valorefuturo);
        setQuantitaScommessa(quantitaScommessa);
        setIndirizzoPortafoglio(indirizzoportafoglio);
    }

    public void setValoreFuturo(String valorefuturo){
        this.valorefuturo = valorefuturo;
    }
    public void setQuantitaScommessa(String quantitaScommessa){
        this.quantitascommessa = quantitaScommessa;
    }
    public void setIndirizzoPortafoglio(String indirizzoPortafoglio){
        this.indirizzoportafoglio = indirizzoPortafoglio;
    }

    public String getValoreFuturo(){
        return valorefuturo;
    }
    public String getQuantitaScommessa(){
        return quantitascommessa;
    }
    public String getIndirizzoPortafoglio(){
        return indirizzoportafoglio;
    }

}
