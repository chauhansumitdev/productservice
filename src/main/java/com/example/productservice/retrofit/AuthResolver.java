package com.example.productservice.retrofit;


import com.example.productservice.dto.RequestAuth;
import com.example.productservice.dto.ResponseAuth;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@Component
public class AuthResolver {

    AuthInterface authInterface;
    Retrofit retrofit;

    public AuthResolver(Retrofit retrofit){
        this.retrofit = retrofit;
        authInterface = retrofit.create(AuthInterface.class);
    }

    public ResponseAuth verify(String token) throws  Exception{
        Call<ResponseAuth> caller = authInterface.verify(token);
        Response<ResponseAuth> response = caller.execute();

        if(response.isSuccessful()){
            return response.body();
        }

        throw new Exception("INVALID TOKEN");
    }


    public ResponseAuth generateToken(RequestAuth requestAuth) throws  Exception{
        Call<ResponseAuth> caller = authInterface.generate(requestAuth);
        Response<ResponseAuth> response = caller.execute();

        if(response.isSuccessful()){
            return  response.body();
        }

        throw new Exception("INVALID TOKEN");
    }

}
