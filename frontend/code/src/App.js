import logo from './logo.svg';
import './App.css';
import { Button, ThemeProvider } from '@mui/material';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import Navbar from './customer/component/Navbar/Navbar.tsx';
import customeTheme from './Theme/customTheme.ts';

export default function App() {
  return (
    <div>

      <ThemeProvider theme={customeTheme}>

        <div>
          <Navbar />
        </div>

      </ThemeProvider>



    </div>


  )
}
