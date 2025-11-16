# biblioteca-de-manga-api

classDiagram
    direction LR

    %% Entidades (Modelos Limpos)
    class Autor {
        -Long id
        -String nome
    }
    class Usuario {
        -Long id
        -String nome
        -String email
        -String senhaCriptografada
        +validarSenha(String senha) boolean
    }
    class Obra {
        -Long id
        -String titulo
        -Autor autor
        -String genero
        -int anoPublicacao
        -String caminhoArquivo
    }
    
    %% Camadas de Serviço (Validação e Regras de Negócio)
    class UsuarioService {
        +criarUsuario(...) Usuario
        +validarSenha(String senha)
        +validarEmail(String email)
    }
    class AutorService {
        +criarAutor(Long id, String nome) Autor
        +validarNome(String nome)
    }
    class ObraService {
        +criarObra(...) Obra
        +validarAnoPublicacao(int ano)
    }

    %% Serviço Funcional e Repositório
    class AuthService {
        -List<Usuario> usuarios
        -UsuarioService usuarioService
        +login(String email, String senha) Usuario
        +cadastrar(...) Usuario
    }

    class MangaRepository {
        -List<Obra> obras
        -AutorService autorService
        -ObraService obraService
        +adicionarObra(...) Obra
        +buscarEFiltrar(String termo, String genero) List<Obra>
    }

    %% Relacionamentos
    
    Obra "1" -- "1" Autor : possui
    
    AuthService "1" -- "1" UsuarioService : usa
    MangaRepository "1" -- "1" AutorService : usa
    MangaRepository "1" -- "1" ObraService : usa

    AuthService "1" --> "*" Usuario : gerencia
    MangaRepository "1" --> "*" Obra : armazena
