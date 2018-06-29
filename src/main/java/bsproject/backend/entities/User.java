package bsproject.backend.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    //private String pwd;
    private String hashedPassword;
    private String salt;
    private String email;

    // define the one-to-many condition
    // a user can have multiple self-defined words
    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<OwnWord> ownWords = new ArrayList<OwnWord>();

    // define the one-to-many condition
    // a user will recite many words...
    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<Recited> recitedWords = new ArrayList<Recited>();

    public User(){}

    // some boring get and set...
    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getHashedPassword(){
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword){
        this.hashedPassword = hashedPassword;
    }

    public String getSalt(){
        return salt;
    }

    public void setSalt(String salt){
        this.salt = salt;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public List<OwnWord> getOwnWords(){
        return ownWords;
    }

    public List<Recited> getRecitedWords(){
        return recitedWords;
    }

    public void addRecitedWord(Recited recited){
        recitedWords.add(recited);
        recited.setUser(this);
    }

    public void addOwnWord(OwnWord own){
        ownWords.add(own);
        own.setUser(this);
    }

}