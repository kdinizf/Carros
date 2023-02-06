package service;

import model.Carro;
import org.junit.Assert;
import org.junit.Test;

public class CarroServiceImplTest {

    @Test
    public void deveIniciarDesligado() {

        Carro carro = new Carro("Amarelo", "Corsa", "Hatch", 2010, 150);

        Assert.assertFalse(carro.isLigado());

    }

    @Test
    public void deveLigar() {

        CarroService carroService = new CarroServiceImpl();
        Carro carro = new Carro("Amarelo", "Corsa", "Hatch", 2010, 150);
        carroService.ligar(carro);

        Assert.assertTrue(carro.isLigado());

    }

    @Test
    public void deveDesligar() {

        CarroService carroService = new CarroServiceImpl();
        Carro carro = new Carro("Amarelo", "Corsa", "Hatch", 2010, 150);
        carroService.ligar(carro);
        carroService.desligar(carro);

        Assert.assertFalse(carro.isLigado());

    }

    @Test
    public void deveDesligarSomenteComCarroParado() {

        CarroService carroService = new CarroServiceImpl();
        Carro carro = new Carro("Amarelo", "Corsa", "Hatch", 2010, 150);
        carroService.ligar(carro);
        carroService.acelerar(carro, 140);
        carroService.frear(carro, 30);
        carroService.desligar(carro);

        Assert.assertTrue(carro.isLigado());
        Assert.assertEquals(110, carro.getVelocidadeAtual());

        carroService.frear(carro, 110);
        carroService.desligar(carro);

        Assert.assertFalse(carro.isLigado());
        Assert.assertEquals(0, carro.getVelocidadeAtual());

    }

    @Test
    public void deveAcelerarSomenteComCarroLigado() {

        CarroService carroService = new CarroServiceImpl();
        Carro carro = new Carro("Amarelo", "Corsa", "Hatch", 2010, 150);
        carroService.acelerar(carro, 40);

        Assert.assertFalse(carro.isLigado());
        Assert.assertEquals(0, carro.getVelocidadeAtual());

    }

    @Test
    public void deveFrearSomenteComCarroLigado() {

        CarroService carroService = new CarroServiceImpl();
        Carro carro = new Carro("Amarelo", "Corsa", "Hatch", 2010, 150);
        carroService.frear(carro, 50);

        Assert.assertFalse(carro.isLigado());
        Assert.assertEquals(0, carro.getVelocidadeAtual());

    }

    @Test
    public void deveAcelerarCorretamente() {

        CarroService carroService = new CarroServiceImpl();
        Carro carro = new Carro("Amarelo", "Corsa", "Hatch", 2010, 150);
        carroService.ligar(carro);
        carroService.acelerar(carro, 10);

        Assert.assertEquals(10, carro.getVelocidadeAtual());

    }

    @Test
    public void deveTerVelocidadePositivaOuZero() {

        CarroService carroService = new CarroServiceImpl();
        Carro carro = new Carro("Amarelo", "Corsa", "Hatch", 2010, 150);
        carroService.ligar(carro);
        carroService.acelerar(carro, 20);
        carroService.frear(carro, 30);

        Assert.assertEquals(0, carro.getVelocidadeAtual());

    }

    @Test
    public void naoDevePassarDaVelocidadeMaxima() {

        CarroService carroService = new CarroServiceImpl();
        Carro carro = new Carro("Amarelo", "Corsa", "Hatch", 2010, 150);
        Carro carro2 = new Carro("Amarelo", "Corsa", "Hatch", 2010, 150);
        carroService.ligar(carro);
        carroService.ligar(carro2);
        carroService.acelerar(carro, 170);
        carroService.acelerar(carro2, 50);

        Assert.assertEquals(150, carro.getVelocidadeAtual());
        Assert.assertEquals(50, carro2.getVelocidadeAtual());

    }

}
