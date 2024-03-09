import { configureStore } from '@reduxjs/toolkit';
import jwtReducer from '../reducers/jwtReducer';

export default configureStore({
  reducer: {
    jwt: jwtReducer,
  },
});
