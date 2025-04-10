package cadeia.util;

import java.util.concurrent.locks.*;

public class EsteiraCircular<T> {
    private final Object[] buffer;
    private int inicio = 0;
    private int fim = 0;
    private int tamanho = 0;
    private final int capacidade;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public EsteiraCircular(int capacidade) {
        this.capacidade = capacidade;
        this.buffer = new Object[capacidade];
    }

    // Método principal para inserir e retornar a posição onde foi inserido
    public int inserir(T item) throws InterruptedException {
        lock.lock();
        try {
            while (tamanho == capacidade) {
                notFull.await(); // Espera espaço livre
            }
            buffer[fim] = item;
            int posicaoInserida = fim;
            fim = (fim + 1) % capacidade;
            tamanho++;
            notEmpty.signalAll(); // Notifica que não está mais vazia
            return posicaoInserida;
        } finally {
            lock.unlock();
        }
    }

    // Versão alternativa que não retorna a posição (por compatibilidade, opcional)
    public int adicionar(T item) throws InterruptedException {
        return inserir(item);
    }

    public T remover() throws InterruptedException {
        lock.lock();
        try {
            while (tamanho == 0) {
                notEmpty.await(); // Espera até que haja item
            }
            @SuppressWarnings("unchecked")
            T item = (T) buffer[inicio];
            inicio = (inicio + 1) % capacidade;
            tamanho--;
            notFull.signalAll(); // Notifica que não está mais cheia
            return item;
        } finally {
            lock.unlock();
        }
    }

    public int getTamanho() {
        lock.lock();
        try {
            return tamanho;
        } finally {
            lock.unlock();
        }
    }
}
