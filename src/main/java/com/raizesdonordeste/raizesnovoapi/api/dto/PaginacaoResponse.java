package com.raizesdonordeste.raizesnovoapi.api.dto;

import java.util.List;

public class PaginacaoResponse<T> {

    private List<T> itens;
    private int pagina;
    private int totalPaginas;
    private long totalItens;

    public List<T> getItens() {
        return itens;
    }

    public void setItens(List<T> itens) {
        this.itens = itens;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public long getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(long totalItens) {
        this.totalItens = totalItens;
    }
}
