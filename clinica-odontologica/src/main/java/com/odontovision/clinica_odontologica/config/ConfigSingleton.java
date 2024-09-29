package com.odontovision.clinica_odontologica.config;

/**
 * Classe Singleton para gerenciar as configurações da aplicação.
 * <p>
 * O padrão Singleton garante que apenas uma instância desta classe seja criada,
 * fornecendo um ponto de acesso global às configurações.
 * </p>
 */
public class ConfigSingleton {

    /**
     * Instância única da classe ConfigSingleton.
     */
    private static ConfigSingleton instance;

    /**
     * Variável para armazenar a configuração da aplicação.
     */
    private String config;

    /**
     * Construtor privado para evitar que a classe seja instanciada diretamente.
     * O padrão Singleton utiliza este construtor privado para garantir que apenas
     * uma instância seja criada.
     */
    private ConfigSingleton() {}

    /**
     * Método para obter a instância única da classe ConfigSingleton.
     * <p>
     * Se a instância ainda não foi criada, este método cria uma nova instância.
     * Caso contrário, retorna a instância existente.
     * </p>
     *
     * @return a instância única da classe ConfigSingleton.
     */
    public static ConfigSingleton getInstance() {
        if (instance == null) {
            instance = new ConfigSingleton();
        }
        return instance;
    }

    /**
     * Retorna a configuração armazenada.
     *
     * @return a configuração atual.
     */
    public String getConfig() {
        return config;
    }

    /**
     * Define uma nova configuração para a aplicação.
     *
     * @param config a nova configuração a ser definida.
     */
    public void setConfig(String config) {
        this.config = config;
    }
}
