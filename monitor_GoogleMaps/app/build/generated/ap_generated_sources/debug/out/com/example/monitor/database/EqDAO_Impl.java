package com.example.monitor.database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.monitor.Earthquake;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class EqDAO_Impl implements EqDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Earthquake> __insertionAdapterOfEarthquake;

  private final EntityDeletionOrUpdateAdapter<Earthquake> __deletionAdapterOfEarthquake;

  private final EntityDeletionOrUpdateAdapter<Earthquake> __updateAdapterOfEarthquake;

  public EqDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEarthquake = new EntityInsertionAdapter<Earthquake>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `earthquakes` (`id`,`place`,`magnitude`,`time`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Earthquake value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getPlace() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPlace());
        }
        stmt.bindDouble(3, value.getMagnitude());
        stmt.bindLong(4, value.getTime());
        stmt.bindDouble(5, value.getLatitude());
        stmt.bindDouble(6, value.getLongitude());
      }
    };
    this.__deletionAdapterOfEarthquake = new EntityDeletionOrUpdateAdapter<Earthquake>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `earthquakes` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Earthquake value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfEarthquake = new EntityDeletionOrUpdateAdapter<Earthquake>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `earthquakes` SET `id` = ?,`place` = ?,`magnitude` = ?,`time` = ?,`latitude` = ?,`longitude` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Earthquake value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getPlace() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPlace());
        }
        stmt.bindDouble(3, value.getMagnitude());
        stmt.bindLong(4, value.getTime());
        stmt.bindDouble(5, value.getLatitude());
        stmt.bindDouble(6, value.getLongitude());
        if (value.getId() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getId());
        }
      }
    };
  }

  @Override
  public void insertAll(final List<Earthquake> eqList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEarthquake.insert(eqList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteEarthquake(final Earthquake eq) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfEarthquake.handle(eq);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateEarthquake(final Earthquake eq) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfEarthquake.handle(eq);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Earthquake>> getEarthquakes() {
    final String _sql = "SELECT * FROM earthquakes";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"earthquakes"}, false, new Callable<List<Earthquake>>() {
      @Override
      public List<Earthquake> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPlace = CursorUtil.getColumnIndexOrThrow(_cursor, "place");
          final int _cursorIndexOfMagnitude = CursorUtil.getColumnIndexOrThrow(_cursor, "magnitude");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final List<Earthquake> _result = new ArrayList<Earthquake>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Earthquake _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpPlace;
            if (_cursor.isNull(_cursorIndexOfPlace)) {
              _tmpPlace = null;
            } else {
              _tmpPlace = _cursor.getString(_cursorIndexOfPlace);
            }
            final double _tmpMagnitude;
            _tmpMagnitude = _cursor.getDouble(_cursorIndexOfMagnitude);
            final long _tmpTime;
            _tmpTime = _cursor.getLong(_cursorIndexOfTime);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            _item = new Earthquake(_tmpId,_tmpPlace,_tmpMagnitude,_tmpTime,_tmpLatitude,_tmpLongitude);
            _result.add(_item);
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
  public LiveData<List<Earthquake>> getEarthquakesWithMagnitude(final double mag) {
    final String _sql = "SELECT * FROM earthquakes WHERE magnitude > ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindDouble(_argIndex, mag);
    return __db.getInvalidationTracker().createLiveData(new String[]{"earthquakes"}, false, new Callable<List<Earthquake>>() {
      @Override
      public List<Earthquake> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPlace = CursorUtil.getColumnIndexOrThrow(_cursor, "place");
          final int _cursorIndexOfMagnitude = CursorUtil.getColumnIndexOrThrow(_cursor, "magnitude");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final List<Earthquake> _result = new ArrayList<Earthquake>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Earthquake _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpPlace;
            if (_cursor.isNull(_cursorIndexOfPlace)) {
              _tmpPlace = null;
            } else {
              _tmpPlace = _cursor.getString(_cursorIndexOfPlace);
            }
            final double _tmpMagnitude;
            _tmpMagnitude = _cursor.getDouble(_cursorIndexOfMagnitude);
            final long _tmpTime;
            _tmpTime = _cursor.getLong(_cursorIndexOfTime);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            _item = new Earthquake(_tmpId,_tmpPlace,_tmpMagnitude,_tmpTime,_tmpLatitude,_tmpLongitude);
            _result.add(_item);
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
