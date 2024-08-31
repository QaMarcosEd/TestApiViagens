package com.montanha.gerenciador.viagens;

import com.montanha.gerenciador.utils.DetalhesViagem;
import com.montanha.gerenciador.utils.TokenViagem;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;

public class ViagensTest {
    @Before
    public void setUp() {
        // Configurar o caminho comum de acesso a minha api rest
        baseURI = "http://localhost";
        port = 8089;
        basePath = "/api";
    }

    // Cadastrar viagem como admin
    @Test
    public void testDadoUmAdministradorQuandoCadastroViagensEntaoObtenhoStatusCode201() {
        // Obter o token de administrador
        String token = TokenViagem.obterTokenAdmin();

        // Cadastrar a viagem
        given()
                .header("Authorization", token)
                .body(DetalhesViagem.toJson(
                        "Josefalda",
                        "2024-08-29",
                        "2024-11-20",
                        "Manaus",
                        "Norte"
                ))
                .contentType(ContentType.JSON)
                .when()
                .post("/v1/viagens")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201);


    }

    //Cadastrar viagem como usuário comum
    @Test
    public void testDadoUmUsuarioComumQuandoCadastraViagemEntaoObtenhoStatusCode403() {
        String token = TokenViagem.obterTokenUsuario();

        given()
        .header("Authorization", token)
                .body(DetalhesViagem.toJson(
                        "Josefalda",
                        "2024-08-29",
                        "2024-11-20",
                        "Manaus",
                        "Norte"
                ))
                .contentType(ContentType.JSON)
                .when()
                .post("/v1/viagens")
                .then()
                .log().all()
                .assertThat()
                .statusCode(403);
    }

    //Cadastrar viagem sem um token válido
    @Test
    public void testDadoCadastrarViagemSemTokenValidoEntaoDeveRetornarStatus401() {
        String token = "5465432165747894.fg45f6g4d35f4";

            given()
            .header("Authorization", token)
                    .body(DetalhesViagem.toJson(
                            "Josefalda",
                            "2024-08-29",
                            "2024-11-20",
                            "Manaus",
                            "Norte"
                    ))
                    .contentType(ContentType.JSON)
                    .when()
                    .post("/v1/viagens")
                    .then()
                    .log().all()
                    .assertThat()
                    .statusCode(401);
    }

    // Listando viagem autenticado como usuário comum
    @Test
    public void testDadoUmUsuarioQuandoListaViagensEntaoObtenhoStatusCode200() {
        // Obter Token Usuario
        String token = TokenViagem.obterTokenUsuario();

        // Listando viagem como usuario comum
        given()
                .header("Authorization", token)
                .when()
                .get("/v1/viagens")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    // Listando viagem como Admin
    @Test
    public void testDadoUmAdminQuandoListaViagensEntaoObtenhoStatusCode403SemAutorizacao() {
        // Obter Token Usuario
        String token = TokenViagem.obterTokenAdmin();

        // Listando viagem como usuario comum
        given()
                .header("Authorization", token)
                .when()
                .get("/v1/viagens")
                .then()
                .log().all()
                .assertThat()
                .statusCode(403);
    }

    // litando viagem especifica pelo ID
    @Test
    public void testDadoUmUsuarioQuandoListaViagemComIdInexistenteEntãoObetnhoStatusCode404() {
       String token = TokenViagem.obterTokenUsuario();

       given()
       .header("Authorization", token)
               .when()
               .get("/v1/viagens/999")
               .then()
               .log().all()
               .assertThat()
               .statusCode(404);
    }

    // VIAGEM É ATUALIZADA MAS API NÃO RETORNA RESPOSE RECEBENDO STATUS 204
    @Test
    public void testDadoUmAdminQuandoAtualizaUmaViagemPeloIDEntaoObtenhoAViagemComStatus204() {
        // Obter Token Admin
        String token = TokenViagem.obterTokenAdmin();

        given()
        .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(DetalhesViagem.toJson(
                        "Naruto",
                        "2024-08-29",
                        "2024-11-10",
                        "Manaus",
                        "Norte"
                ))
                .when()
                .put("/v1/viagens/1")
                .then()
                .log().status();
    }

    // VIAGEM É DELETADA MAS A API NÃO RETORNA NENHUMA RESPONSE RECEBENDO STATUS 204
    @Test
    public void testDadoUmAdminQuandoDeletaUmaViagemPeloIdEntaoAViagemEDeletadaEReceboStatus204() {
        String token = TokenViagem.obterTokenAdmin();

        given()
        .header("Authorization", token)
                .when()
                .delete("/v1/viagens/1")
                .then()
                .log().all()
                .assertThat()
                .statusCode(204);
    }

    // DELETANDO VIAGEM COM ID INEXISTENTE [ RECEBE STATUS 500, ROTA SEM TRATAMENTO DE ERRO]
}