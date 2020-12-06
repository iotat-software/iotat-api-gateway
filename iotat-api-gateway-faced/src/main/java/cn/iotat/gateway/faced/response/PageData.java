package cn.iotat.gateway.faced.response;

import cn.iotat.gateway.core.model.ToString;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 分页的基本返回，注意该类并不能作为{@link cn.iotat.producer.faced.response.BaseResponse}的替代
 * 而是应该作为其参数传入
 *
 * @author pang
 */
public class PageData<E> extends ToString implements Iterable<E>{
    private static final long serialVersionUID = 414202552389263186L;
    /**
     * 页长
     */
    private int pageSize;
    /**
     * 页码
     */
    private int pageNo;
    /**
     * 列表总数
     */
    private int totalCount;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 是否包含下一页
     */
    private boolean hasNext;
    /**
     * 数据集合
     */
    private Collection<E> data;

    /**
     * 生成分页数据
     *
     * @param data       数据
     * @param pageNum    页码
     * @param pageSize   页长
     * @param totalCount 数据总量
     * @return 分页数据
     */
    public static <E> PageData<E> genPageResponse(Collection<E> data, int pageNum, int pageSize, int totalCount) {
        PageData<E> pageData = new PageData<>();
        pageData.setData(data);
        pageData.setPageNo(pageNum);
        pageData.setPageSize(pageSize);
        pageData.setTotalCount(totalCount);
        int totalPage = totalCount / pageSize;
        pageData.setTotalPage(totalPage);
        boolean hasNext = pageNum < totalPage;
        pageData.setHasNext(hasNext);
        return pageData;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public Collection<E> getData() {
        return data;
    }

    public void setData(Collection<E> data) {
        this.data = data;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    @Override
    public Iterator<E> iterator() {
        return data.iterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        data.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return data.spliterator();
    }
}
