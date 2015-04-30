package com.just.lollipop.bbs.dao.impl;

import com.just.lollipop.bbs.dao.ColumnDao;
import com.just.lollipop.bbs.dao.base.impl.HibernateDaoImpl;
import com.just.lollipop.bbs.domain.Column;
import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.vo.ColumnVo;

import java.util.ArrayList;
import java.util.List;


/**
 * 版块数据访问实现类
 */
public class ColumnDaoImpl extends HibernateDaoImpl<Column>
        implements ColumnDao {

    @Override
    public List<Column> findColumns(ColumnVo columnVo, int start, int pagesize)
            throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM Column col ");
        sb.append("WHERE 1=1 ");
        List<Object> params = new ArrayList<Object>();
        if (columnVo != null) {
            if (columnVo.getName() != null && !"".equals(columnVo.getName())) {
                sb.append("AND col.name like ? ");
                params.add("%" + columnVo.getName() + "%");
            }
        }
        return this.findByPage(sb.toString(), start, pagesize, params.toArray());
    }

    @Override
    public int getTotalOfColumns(ColumnVo columnVo) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(col.id) ");
        sb.append("FROM Column col ");
        sb.append("WHERE 1=1 ");
        List<Object> params = new ArrayList<Object>();
        if (columnVo != null) {
            if (columnVo.getName() != null && !"".equals(columnVo.getName())) {
                sb.append("AND col.name like ? ");
                params.add("%" + columnVo.getName() + "%");
            }
        }
        return this.count(sb.toString(), params.toArray());
    }

    @Override
    public void addColumn(Column column) throws PtException {
        this.save(column);
    }

    @Override
    public void updateColumn(Column column) throws PtException {
        this.saveOrUpdate(column);
    }

    @Override
    public void deleteColumn(int columnId) throws PtException {
        this.delete(getColumn(columnId));
    }

    @Override
    public Column getColumn(int columnId) throws PtException {
        return this.get(Column.class, columnId);
    }

    @Override
    public List<Column> getColumns() throws PtException {
        return this.find(Column.class);
    }

}
