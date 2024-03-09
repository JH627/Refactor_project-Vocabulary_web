import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import { QueryClientProvider } from '@tanstack/react-query';
import { queryClient } from './util/api';

import Main from './pages/Main';
import Auth from './pages/Auth';
import Test from './pages/Test';
import Words from './pages/Words';
import './App.css';
import RootLayout from './pages/Root';
import Logout from './pages/Logout';

const router = createBrowserRouter([
  {
    path: '/',
    element: <RootLayout />,
    children: [
      {
        path: '/auth',
        element: <Auth />,
      },
      {
        path: '/main',
        element: <Main />,
      },
      {
        path: '/test',
        element: <Test />,
      },
      {
        path: '/words',
        element: <Words />,
      },
      {
        path: '/logout',
        element: <Logout />,
      },
    ],
  },
]);

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <RouterProvider router={router} />
    </QueryClientProvider>
  );
}

export default App;
