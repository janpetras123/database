CREATE TABLE IF NOT EXISTS tim (
	id SERIAL PRIMARY KEY,
	nazov VARCHAR(70),
	krajina VARCHAR(70),
	adresa VARCHAR(70)
);

CREATE TABLE IF NOT EXISTS pretekar (
	id SERIAL PRIMARY KEY,
	meno VARCHAR(70),
	priezvisko VARCHAR(70),
	datum_narodenia DATE,
	vaha INTEGER,
	id_tim INTEGER REFERENCES tim(id)
);

CREATE TABLE IF NOT EXISTS miesto_preteku(
	id SERIAL PRIMARY KEY,
	krajina VARCHAR(70),
	adresa VARCHAR(70)
);

CREATE TABLE IF NOT EXISTS pretek (
	id SERIAL PRIMARY KEY,
	max_timov INTEGER,
	datum DATE,
	id_miesto_preteku INTEGER REFERENCES miesto_preteku(id)
);

CREATE TABLE IF NOT EXISTS pretekar_v_preteku (
	id SERIAL PRIMARY KEY,
	cas INTEGER,
	dokonceny BOOLEAN,
	id_pretek INTEGER REFERENCES pretek(id),
	id_pretekar INTEGER REFERENCES pretekar(id)
);

CREATE TABLE IF NOT EXISTS sponzor (
	id SERIAL PRIMARY KEY,
	meno VARCHAR(70),
	vznik DATE,
	link VARCHAR(70)
);

CREATE TABLE IF NOT EXISTS sponzor_timu (
	id SERIAL PRIMARY KEY,
	od_kedy DATE,
	do_kedy DATE,
	level INTEGER,
	id_tim INTEGER REFERENCES tim(id),
	id_sponzor INTEGER REFERENCES sponzor(id)
);
