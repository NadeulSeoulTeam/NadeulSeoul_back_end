package com.alzal.nadeulseoulbackend.global.auth;


import com.alzal.nadeulseoulbackend.domain.users.dto.OAuth2UserInfo;
import com.alzal.nadeulseoulbackend.domain.users.entity.Role;
import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import com.alzal.nadeulseoulbackend.domain.users.repository.UserRepository;
import com.alzal.nadeulseoulbackend.global.auth.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
//    private final HttpSession httpSession;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        System.out.println(oAuth2User);
        System.out.println("client registration id : " + userRequest.getClientRegistration().getRegistrationId());
        try {
            return processOAuth2User(userRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage());
        }

//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
//        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
//        User user = saveOrUpdate(attributes);
//        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), attributes.getAttributes(), attributes.getNameAttributeKey());
    }

//    private User saveOrUpdate(OAuthAttributes attributes) {
//        User user = userRepository.findByEmail(attributes.getEmail()).map(entity -> entity.update(attributes.getName())).orElse(attributes.toEntity());
//        return userRepository.save(user);
//    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {

        OAuth2UserInfo oAuth2UserInfo = new OAuth2UserInfo(oAuth2User.getAttributes());
        Optional<User> optionalUser = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        User user;
        if (optionalUser.isPresent()) {
            // throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
            user = updateExistingUser(optionalUser.get(), oAuth2UserInfo);
            System.out.println("이미 있는 사용자 입니다.");

        } else {
            System.out.println("없는 사용자 입니다.");
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }
        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = User.builder()
                .name(oAuth2UserInfo.getName())
                .email(oAuth2UserInfo.getEmail())
                .role(Role.MEMBER)
                .build();
        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.builder().name(oAuth2UserInfo.getName()).build();
        return userRepository.save(existingUser);
    }

}
