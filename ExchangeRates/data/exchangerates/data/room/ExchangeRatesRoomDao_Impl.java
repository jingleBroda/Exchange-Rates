package com.example.exchangerates.data.room;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.rxjava3.EmptyResultSetException;
import androidx.room.rxjava3.RxRoom;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.exchangerates.data.entity.MoneyRoomModelEntity;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.Void;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ExchangeRatesRoomDao_Impl implements ExchangeRatesRoomDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MoneyRoomModelEntity> __insertionAdapterOfMoneyRoomModelEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllData;

  private final SharedSQLiteStatement __preparedStmtOfDeleteSingleData;

  public ExchangeRatesRoomDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMoneyRoomModelEntity = new EntityInsertionAdapter<MoneyRoomModelEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `elect_currency` (`charCodeId`,`coursCurrencyInRUB`,`lockStatus`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MoneyRoomModelEntity value) {
        if (value.getCharCodeId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getCharCodeId());
        }
        stmt.bindDouble(2, value.getCoursCurrencyInRUB());
        final int _tmp;
        _tmp = value.getLockStatus() ? 1 : 0;
        stmt.bindLong(3, _tmp);
      }
    };
    this.__preparedStmtOfDeleteAllData = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM elect_currency";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteSingleData = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM elect_currency WHERE charCodeId = ?";
        return _query;
      }
    };
  }

  @Override
  public Completable insertCurrency(final MoneyRoomModelEntity electCurrency) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMoneyRoomModelEntity.insert(electCurrency);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable deleteAllData() {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllData.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAllData.release(_stmt);
        }
      }
    });
  }

  @Override
  public Completable deleteSingleData(final String charCode) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteSingleData.acquire();
        int _argIndex = 1;
        if (charCode == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, charCode);
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteSingleData.release(_stmt);
        }
      }
    });
  }

  @Override
  public Single<List<MoneyRoomModelEntity>> getCurrencyCours() {
    final String _sql = "SELECT * FROM elect_currency";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createSingle(new Callable<List<MoneyRoomModelEntity>>() {
      @Override
      public List<MoneyRoomModelEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCharCodeId = CursorUtil.getColumnIndexOrThrow(_cursor, "charCodeId");
          final int _cursorIndexOfCoursCurrencyInRUB = CursorUtil.getColumnIndexOrThrow(_cursor, "coursCurrencyInRUB");
          final int _cursorIndexOfLockStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "lockStatus");
          final List<MoneyRoomModelEntity> _result = new ArrayList<MoneyRoomModelEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final MoneyRoomModelEntity _item;
            final String _tmpCharCodeId;
            if (_cursor.isNull(_cursorIndexOfCharCodeId)) {
              _tmpCharCodeId = null;
            } else {
              _tmpCharCodeId = _cursor.getString(_cursorIndexOfCharCodeId);
            }
            final double _tmpCoursCurrencyInRUB;
            _tmpCoursCurrencyInRUB = _cursor.getDouble(_cursorIndexOfCoursCurrencyInRUB);
            final boolean _tmpLockStatus;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfLockStatus);
            _tmpLockStatus = _tmp != 0;
            _item = new MoneyRoomModelEntity(_tmpCharCodeId,_tmpCoursCurrencyInRUB,_tmpLockStatus);
            _result.add(_item);
          }
          if(_result == null) {
            throw new EmptyResultSetException("Query returned empty result set: " + _statement.getSql());
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Single<MoneyRoomModelEntity> getSpecificCurrencyCours(final String charCode) {
    final String _sql = "SELECT * FROM elect_currency WHERE charCodeId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (charCode == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, charCode);
    }
    return RxRoom.createSingle(new Callable<MoneyRoomModelEntity>() {
      @Override
      public MoneyRoomModelEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCharCodeId = CursorUtil.getColumnIndexOrThrow(_cursor, "charCodeId");
          final int _cursorIndexOfCoursCurrencyInRUB = CursorUtil.getColumnIndexOrThrow(_cursor, "coursCurrencyInRUB");
          final int _cursorIndexOfLockStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "lockStatus");
          final MoneyRoomModelEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpCharCodeId;
            if (_cursor.isNull(_cursorIndexOfCharCodeId)) {
              _tmpCharCodeId = null;
            } else {
              _tmpCharCodeId = _cursor.getString(_cursorIndexOfCharCodeId);
            }
            final double _tmpCoursCurrencyInRUB;
            _tmpCoursCurrencyInRUB = _cursor.getDouble(_cursorIndexOfCoursCurrencyInRUB);
            final boolean _tmpLockStatus;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfLockStatus);
            _tmpLockStatus = _tmp != 0;
            _result = new MoneyRoomModelEntity(_tmpCharCodeId,_tmpCoursCurrencyInRUB,_tmpLockStatus);
          } else {
            _result = null;
          }
          if(_result == null) {
            throw new EmptyResultSetException("Query returned empty result set: " + _statement.getSql());
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
