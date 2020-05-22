package br.com.jedisdemo;

import redis.clients.jedis.Jedis;

import java.util.Map;

public class ExecutaJedisDemo {

    private final static String REDIS_HOST = "localhost";
    private final static Integer REDIS_PORT = 6379;

    public static void main(final String args[]) {
        // Objeto jedis utilizado para fazer as operações no REDIS
        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);

        //Adiciona "Hello World!" na chave teste
        jedis.set("teste", "Hello World!");
        System.out.println("Valor da chave teste: " + jedis.get("teste"));
        //Deleta teste
        jedis.del("teste");
        System.out.println("Valor da chave teste após deletar: " + jedis.get("teste"));

        //**********  Exemplo com incrementos  **********
        jedis.set("cont", "1");
        System.out.println("Valor de cont: " + jedis.get("cont")); // = 1
        jedis.incr("cont");
        System.out.println("Valor de cont: " + jedis.get("cont")); // = 2
        jedis.incrBy("cont", 10);
        System.out.println("Valor de cont: " + jedis.get("cont")); // = 12
        jedis.decr("cont");
        System.out.println("Valor de cont: " + jedis.get("cont")); // = 11
        jedis.decrBy("cont", 5);
        System.out.println("Valor de cont: " + jedis.get("cont")); // = 6

        //**********  Exemplo com listas  **********
        // Adiciona um elemento no final da lista
        jedis.rpush("lista#users", "User1");
        jedis.rpush("lista#users", "User2");

        // Adiciona um elemento no inicio da lista
        jedis.lpush("lista#users", "user0");

        //Pega todos os elementos da lista
        System.out.println("Lista de usuários: " + jedis.lrange("lista#users", 0, -1));

        //**********  Exemplo com hashes  **********
        //Cria hash car1
        jedis.hset("car1", "marca", "Ford");
        jedis.hset("car1", "nome", "Mustang");

        //Utiliza hget para pegar os tributos do hash
        System.out.println("Carro 1: " + jedis.hget("car1", "marca") + " "
                        + jedis.hget("car1", "nome"));

        //Cria hash car2
        jedis.hset("car2", "marca", "Volkswagen");
        jedis.hset("car2", "nome", "Kombi");

        //Pega todos os atributos do hash em um map
        Map<String, String> car2 = jedis.hgetAll("car2");
        System.out.println("Carro 2: " + car2.get("marca") + " " + car2.get("nome"));


    }
}
