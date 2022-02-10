package com.alzal.nadeulseoulbackend.global.config.auth.service;


import com.alzal.nadeulseoulbackend.domain.users.dto.OAuth2UserInfo;
import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import com.alzal.nadeulseoulbackend.domain.users.entity.UserRepository;
import com.alzal.nadeulseoulbackend.global.config.auth.dto.*;
import com.alzal.nadeulseoulbackend.global.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.security.AuthProvider;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
//    private final HttpSession httpSession;
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        OAuth2AuthorizedClient clientInfo = authorizedClientService.loadAuthorizedClient("google",authentication.getName());
        OAuth2RefreshToken refreshToken = clientInfo.getRefreshToken();
        System.out.println(refreshToken);
        System.out.println(oAuth2User);
        System.out.println(userRequest.getClass());
        try{
            return processOAuth2User(userRequest,oAuth2User);
        }catch(AuthenticationException ex){
            throw ex;
        }catch(Exception ex){
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
        if(optionalUser.isPresent()){
            // throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
            user = updateExistingUser(optionalUser.get(),oAuth2UserInfo);
            System.out.println("이미 있는 사용자 입니다.");

            if(optionalUser.get().getEmoji()==""){
                //회원가입으로 이동
                System.out.println("회원가입으로 이동해야 하는 유저정보 입니다.");
            }
        }else{
            System.out.println("없는 사용자 입니다.");
            user = registerNewUser(oAuth2UserRequest,oAuth2UserInfo);
        }
        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
            User user = User.builder()
                    .name(oAuth2UserInfo.getName())
                    .email(oAuth2UserInfo.getEmail())
                    .build();
            return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.builder().name(oAuth2UserInfo.getName()).build();
        return userRepository.save(existingUser);
    }

}
