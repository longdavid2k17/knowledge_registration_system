package register.system.knowledgeregistersystem.Models;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Message
{
    @NotBlank
    private String message;

    public Message(@NotBlank String message)
    {
        this.message = message;
    }
}
