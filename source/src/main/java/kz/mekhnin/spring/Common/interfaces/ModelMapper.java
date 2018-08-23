package kz.mekhnin.spring.Common.interfaces;

public interface ModelMapper<TSrc, TDst> {
    void Map(TSrc src, TDst dst);
}
