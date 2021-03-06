package register.system.knowledgeregistersystem.Models;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class User
{
    private Long userId;
    private Date createdAt;

    @NotNull
    @Size(min = 3, message = "Imie musi mieć przynajmniej 3 litery")
    private String name;

    @NotNull
    @NotBlank(message = "Pole adresu email nie może pozostać puste!")
    @Email
    private String email;

    @NotNull
    @Size(min = 4)
    private String interestedAt;

    public User(Long userId, Date createdAt, @NotNull @Size(min = 3, message = "Imie musi mieć przynajmniej 3 litery") String name,
                @NotNull @NotBlank(message = "Pole adresu email nie może pozostać puste!") @Email String email,
                @NotNull @Size(min = 4) String interestedAt)
    {
        this.userId = userId;
        this.createdAt = createdAt;
        this.name = name;
        this.email = email;
        this.interestedAt = interestedAt;
    }

    public User()
    {

    }

    public User(@NotNull @Size(min = 4) String interestedAt)
    {
        this.interestedAt = interestedAt;
    }

    public String getInterestedAt()
    {
        return interestedAt;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
