package com.luhn;

enum NumberTypes {
    Personummer, Samordningsnummer, Organisationsnummer, Undefined
}

class Result {
    public final boolean IsValid;
    public final NumberTypes NumberType;
    
    private Result(boolean valid, NumberTypes type){
        IsValid = valid;
        NumberType = type;
    }

    public static Result Invalid = new Result(false, NumberTypes.Undefined);
    public static Result ValidPersonnummer = new Result(true, NumberTypes.Personummer);
    public static Result ValidSamordningsnummer = new Result(true, NumberTypes.Samordningsnummer);
    public static Result ValidOrganisationsnummer = new Result(true, NumberTypes.Organisationsnummer);

    @Override
    public boolean equals(Object o) {
         if (o == this) {
            return true;
        }
 
        if (!(o instanceof Result)) {
            return false;
        }
        
        var r = (Result) o;

        return this.IsValid == r.IsValid && this.NumberType == r.NumberType;
    }
}


