package uz.kh.dish.service;

import uz.kh.dish.dao.request.SignInRequest;
import uz.kh.dish.dao.request.SignUpRequest;
import uz.kh.dish.dao.response.JwtAuthenticationResponse;
import uz.kh.dish.dao.response.SignInResponse;

public interface AuthenticationService {
    SignInResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}
