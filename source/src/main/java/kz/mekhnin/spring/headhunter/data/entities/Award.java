package kz.mekhnin.spring.headhunter.data.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Awards")
public class Award {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "curriculumVitaeId",
            referencedColumnName = "id"
    )
    private CurriculumVitae curriculumVitae;

    @NotBlank
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "userId",
            referencedColumnName = "id",
            nullable = false
    )
    private User user;

    public Award(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
        //prevent endless loop
        if (sameAsFormer(curriculumVitae))
            return ;
        //set new owner
        CurriculumVitae oldOwner = this.curriculumVitae;
        this.curriculumVitae = curriculumVitae;
        //remove from the old owner
        if (oldOwner!=null)
            oldOwner.removeAward(this);
        //set myself into new owner
        if (curriculumVitae!=null)
            curriculumVitae.addAward(this);
    }
    private boolean sameAsFormer(CurriculumVitae newOwner) {
        return this.curriculumVitae==null? newOwner == null : this.curriculumVitae.equals(newOwner);
    }

    public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Award))
            return false;

        final Award a = (Award)object;

        if (id != null && a.getId() != null) {
            return id.equals(a.getId());
        }
        return false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        //prevent endless loop
        if (sameUserAsFormer(user))
            return ;
        //set new owner
        User oldOwner = this.user;
        this.user = user;
        //remove from the old owner
        if (oldOwner!=null)
            oldOwner.removeCurriculumVitae(this);
        //set myself into new owner
        if (user!=null)
            user.addCurriculumVitae(this);
    }

    private boolean sameUserAsFormer(User newOwner) {
        return this.user==null? newOwner == null : this.user.equals(newOwner);
    }
}
