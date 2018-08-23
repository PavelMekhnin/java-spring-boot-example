package kz.mekhnin.spring.Common.interfaces;
// common mapper interface
public interface ModelFactory<TSrc, TDst> {
    public TDst create(TSrc src);
}
