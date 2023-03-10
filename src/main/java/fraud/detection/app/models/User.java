package fraud.detection.app.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@Entity
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="UsersTable")
public class User implements UserDetails {

    @Id
    @GeneratedValue
            (strategy = GenerationType.AUTO)
//    General info
    private Long id;
    @NotEmpty(message = "This field 'model' should not be empty")
    private String firstName;
    @NotNull(message = "This field 'model' should not be empty")
    private String middleName;
    @NotNull(message = "This field 'model' should not be empty")
    private String lastName;
    @NotNull(message = "This field 'model' should not be empty")
    private String dateOfBirth;
    @NotNull(message = "This field 'model' should not be empty")
    private String gender;
    @NotNull(message = "This field 'model' should not be empty")
    private String password;
    @NotNull(message = "This field 'model' should not be empty")
    private String occupation;
    @NotNull(message = "This field 'model' should not be empty")
//    Contact Information
    private String permanentAddress;
    @NotNull(message = "This field 'model' should not be empty")
    private String currentAddress;
    @NotNull(message = "This field 'model' should not be empty")
    @Column(unique = true)
    private String mobileNumber;
    @NotNull(message = "This field 'model' should not be empty")
    private String email;
    @NotNull(message = "This field 'model' should not be empty")
    private String pinCode;
    @NotNull(message = "This field 'model' should not be empty")
    private String city;
    @NotNull(message = "This field 'model' should not be empty")
    private String state;
    @NotNull(message = "This field 'model' should not be empty")
    private String country;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mobileNumber ;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
