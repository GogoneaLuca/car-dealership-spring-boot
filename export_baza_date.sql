-- =============================================
-- PROIECT: ELITE AUTO DEALERSHIP
-- SCRIPT GENERARE STRUCTURA BAZA DE DATE
-- Developer: Gogonea Luca
-- =============================================

-- 1. TABELA ANGAJATI (Utilizatorii aplicatiei)
-- Stocheaza datele de autentificare si rolurile
CREATE TABLE Angajati (
    AngajatID INT PRIMARY KEY IDENTITY(1,1),
    Nume NVARCHAR(50) NOT NULL,
    Prenume NVARCHAR(50) NOT NULL,
    Email NVARCHAR(100) UNIQUE NOT NULL,
    Parola NVARCHAR(100) NOT NULL, -- In productie ar trebui criptata
    Functie NVARCHAR(50),
    DataAngajare DATE DEFAULT GETDATE(),
    Salariu DECIMAL(10, 2)
);

-- 2. TABELA CLIENTI (Cumparatorii)
-- Stocheaza datele persoanelor care achizitioneaza vehicule
CREATE TABLE Clienti (
    ClientID INT PRIMARY KEY IDENTITY(1,1),
    Nume NVARCHAR(50) NOT NULL,
    Prenume NVARCHAR(50) NOT NULL,
    CNP NVARCHAR(13) UNIQUE NOT NULL, -- Constrangere de unicitate
    Telefon NVARCHAR(15) NOT NULL,
    Email NVARCHAR(100),
    Adresa NVARCHAR(200)
);

-- 3. TABELA MASINI (Inventarul)
-- Stocheaza detaliile tehnice si comerciale ale vehiculelor
CREATE TABLE Masini (
    MasinaID INT PRIMARY KEY IDENTITY(1,1),
    Marca NVARCHAR(50) NOT NULL,
    Model NVARCHAR(50) NOT NULL,
    AnFabricatie INT CHECK (AnFabricatie >= 1886), -- Prima masina a fost in 1886
    Pret DECIMAL(10, 2) NOT NULL,
    Kilometraj INT,
    Combustibil NVARCHAR(20), -- Benzina, Diesel, Hibrid, Electric
    Culoare NVARCHAR(30),
    NumarSasiu NVARCHAR(17) UNIQUE, -- VIN-ul este unic
    Stare NVARCHAR(20), -- 'Nou' sau 'Utilizat'
    Disponibilitate NVARCHAR(20) DEFAULT 'In Stoc' -- Status curent
);

-- 4. TABELA VANZARI (Tranzactiile)
-- Tabela de legatura (Jonctiune) intre Angajati, Clienti si Masini
CREATE TABLE Vanzari (
    VanzareID INT PRIMARY KEY IDENTITY(1,1),
    DataVanzare DATE DEFAULT GETDATE(),
    PretFinal DECIMAL(10, 2) NOT NULL,
    ModalitatePlata NVARCHAR(50), -- Cash, Transfer, Leasing
    
    -- Chei Straine (Relatii)
    MasinaID INT UNIQUE, -- O masina se vinde o singura data (Relatie 1:1 in contextul vanzarii curente)
    ClientID INT,        -- Un client poate avea mai multe cumparaturi (Relatie M:1)
    AngajatID INT,       -- Un angajat poate face mai multe vanzari (Relatie M:1)

    CONSTRAINT FK_Vanzari_Masini FOREIGN KEY (MasinaID) REFERENCES Masini(MasinaID),
    CONSTRAINT FK_Vanzari_Clienti FOREIGN KEY (ClientID) REFERENCES Clienti(ClientID),
    CONSTRAINT FK_Vanzari_Angajati FOREIGN KEY (AngajatID) REFERENCES Angajati(AngajatID)
);

-- =============================================
-- EXEMPLE DE DATE (OPTIONAL - PENTRU TESTARE)
-- =============================================

-- Inserare Admin
INSERT INTO Angajati (Nume, Prenume, Email, Parola, Functie, Salariu) 
VALUES ('Popescu', 'Ion', 'admin@eliteauto.ro', 'admin123', 'Manager', 5000);

-- Inserare Client Test
INSERT INTO Clienti (Nume, Prenume, CNP, Telefon, Email, Adresa)
VALUES ('Ionescu', 'Maria', '2900101123456', '0722123123', 'maria@email.com', 'Bucuresti, Sector 1');

-- Inserare Masini in Stoc
INSERT INTO Masini (Marca, Model, AnFabricatie, Pret, Kilometraj, Combustibil, Disponibilitate) 
VALUES 
('BMW', 'Seria 3', 2021, 32000, 45000, 'Diesel', 'In Stoc'),
('Audi', 'Q5', 2023, 55000, 10000, 'Hibrid', 'In Stoc'),
('Dacia', 'Logan', 2019, 8500, 120000, 'Benzina', 'In Stoc');