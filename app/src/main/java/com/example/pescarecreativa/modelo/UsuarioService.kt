package com.example.pescarecreativa.modelo

class UsuarioService {
    companion object{
        var listaUsuarios = listOf<Usuario>(
            Usuario("admin", "admin"),
            Usuario("victor", "victor"),
            Usuario("julian", "julian")
        )

        fun esUsuarioValido(user: String, password: String): Boolean {
            var ret = false
            listaUsuarios.forEach {
                if (it.user== user && it.password == password) {
                    ret = true;
                }
            }
            return ret
        }
    }
}