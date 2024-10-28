package com.example.exe201.model;

import com.example.exe201.enums.Role;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name="users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="role")
    private String role;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    private String email;
    @Column(name="middle_name")
    private String middleName;
    @Column(name="img_url")
    private String AvatarURL;
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }
//    public static User getUserInfo(User user) {
//        return User.builder()
//                .username(user.getUsername())
//                .email(user.email)
//                .firstName(user.firstName)
//                .middleName(user.middleName)
//                .lastName(user.lastName)
//                .role(user.role)
//                .build();
//    }
//
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
