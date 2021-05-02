package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Kala

interface SendSelectKalaInterface {
    fun onsuccess(list: List<Kala>)
    fun onerror(t:Throwable)
}