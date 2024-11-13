package edu.curtin.app;
import java.util.*;
import java.util.function.*;
/**
 * DO NOT ADD ANYTHING TO THIS FILE. This is just a utility to supply data to your Assignment 2
 * simulation app. Your code must work with the ORIGINAL version of this file.
 *
 * You need to create a new TownsInput(), then call 'nextMessage()' periodically, which returns the
 * next message in a queue of randomly-generated messages.
 *
 * This code randomly generates messages. Some of the messages are invalid! To help with
 * debugging, you can:
 * (1) Supply a seed for the random number generator to the constructor, to generate
 *     the same sequence of messages each time. Otherwise, the sequence will always be different.
 * (2) Call 'setErrorProbability(n)' to change the frequency of invalid messages. Invalid messages
 *     will be disabled altogether if you call setErrorProbability(0.0) (but this is only for
 *     testing!).
 */
public class TownsInput
{
    private static final double TOWN_FOUNDING_P = 0.1;
    private static final double TOWN_POPULATION_P = 0.1;
    private static final double RAILWAY_CONSTR_P = 0.2;
    private static final double RAILWAY_DUP_P = 0.2;
    private static final double SCREW_UP_P = 0.05;
    private static final double TOWN_PREWORD_P = 0.5;

    private static final Range<Integer> TOWN_INITIAL_POP = Rand.range(10, 1000);
    private static final Range<Double> TOWN_POP_CHANGE = Rand.range(0.9, 1.2);
    private static final Range<Integer> SCREW_UP_STRING_LEN = Rand.range(3, 15);
    private static final Range<Integer> SCREW_UP_POP_RANGE = Rand.range(-1000, 1000);

    private static final String[] TOWN_PREWORDS = {
        "Abbots", "Ash", "Bishops", "Broad", "Burton", "Bury", "Chipping", "Clifton", "Creech",
        "Down", "Dry", "Earls", "East", "Ferry", "Further", "Great", "Greater", "High", "Hither",
        "Hook", "Hythe", "Kingston", "Larch", "Lesser", "Little", "Long", "Low", "Lower", "Market",
        "Marsh", "Marston", "Middle", "Much", "Narrow", "Nether", "New", "Newton", "North",
        "Norton", "Old", "Royal", "South", "Stanton", "Stoke", "Stoney", "Sutton", "Tooting",
        "Upper", "Water", "West"};

    private static final String[] TOWN_PREFIXES = {
        "Ab", "Adder", "Ald", "Alder", "Amers", "An", "And", "Ander", "Apple", "Ard", "Arn", "Ash",
        "Ax", "Ayle", "Bad", "Bal", "Ban", "Bar", "Bar", "Basil", "Batter", "Beck", "Bed", "Bell",
        "Ben", "Ber", "Bew", "Bex", "Bid", "Biff", "Bin", "Black", "Bleck", "Blew", "Blick",
        "Block", "Bor", "Bourne", "Bow", "Brad", "Brain", "Brake", "Bran", "Bray", "Brent",
        "Brick", "Brid", "Brin", "Brink", "Brit", "Broad", "Brock", "Brod", "Broke", "Broom",
        "Brox", "Buck", "Bud", "Bur", "Burn", "Butter", "By", "Cab", "Cad", "Car", "Cam", "Castor",
        "Catter", "Chal", "Challow", "Chap", "Char", "Charl", "Chart", "Chat", "Chaw", "Cher",
        "Chert", "Chet", "Chew", "Chil", "Chin", "Chin", "Chip", "Chir", "Chit", "Chol", "Chop",
        "Chor", "Chorl", "Chort", "Clatter", "Cleve", "Click", "Cob", "Cock", "Col", "Coln",
        "Crick", "Crimp", "Crop", "Croy", "Crum", "Cul", "Dag", "Dal", "Dart", "Deck", "Den",
        "Dent", "Dept", "Dere", "Dew", "Dib", "Dick", "Dids", "Dig", "Dim", "Dirt", "Ditch", "Dog",
        "Dor", "Dow", "Draft", "Dray", "Drew", "Drift", "Drink", "Drows", "Drum", "Dun", "Dunt",
        "Durs", "Eal", "Eas", "Ebb", "Eccles", "Edge", "Eff", "Eg", "Elm", "End", "Es", "Etten",
        "Ever", "Ew", "Ex", "Ey", "Eyle", "Eyr", "Fal", "Fallow", "Falter", "Far", "Farrow",
        "Fast", "Faxley", "Felt", "Fennel", "Fenney", "Fer", "Fettle", "Fickle", "Finch", "Fish",
        "Fodder", "Folley", "Foss", "Fowl", "Fox", "Fram", "Frat", "Frew", "Frog", "From",
        "Fuller", "Furrow", "Fus", "Gaf", "Gall", "Gam", "Gap", "Gar", "Gars", "Garter", "Gidden",
        "Giff", "Gil", "Glos", "God", "Gold", "Gors", "Gos", "Gray", "Green", "Gres", "Grim",
        "Grimp", "Gristle", "Grittle", "Gros", "Had", "Haggle", "Hale", "Ham", "Han", "Han", "Hap",
        "Hart", "Hat", "Hatch", "Haw", "Hay", "Hazel", "Heath", "Hen", "Hew", "Hib", "Higgen",
        "Hil", "Hinter", "Hitch", "Hobbes", "Hog", "Hol", "Horn", "Hors", "Hox", "Hunt", "Hut",
        "Hux", "Ibs", "Il", "Inch", "Inns", "Ips", "Itch", "Kil", "Kin", "Kirk", "Kneb", "Knob",
        "Knock", "Knox", "Knuckle", "Laden", "Lag", "Lam", "Lang", "Lar", "Lark", "Latch", "Law",
        "Lea", "Lech", "Led", "Leden", "Leg", "Lem", "Lew", "Lewis", "Ley", "Lich", "Lily",
        "Liver", "Lock", "Lov", "Lowe", "Lox", "Luck", "Lud", "Luff", "Lulles", "Lun", "Lye",
        "Mab", "Mackle", "Madder", "Maiden", "Main", "Mal", "Man", "Maps", "March", "Mars",
        "Master", "Match", "Maws", "Med", "Mer", "Mew", "Mickle", "Mine", "Miss", "Mitchin",
        "Mizen", "Moat", "Mobb", "Mock", "Molder", "Mole", "Moll", "Moms", "Mon", "Morr", "Mose",
        "Moss", "Most", "Mother", "Mott", "Mow", "Mox", "Mud", "Muddle", "Mulch", "Mull", "Mum",
        "Mump", "Mun", "Muster", "Mutt", "Muy", "Nab", "Nad", "Naff", "Nag", "Nail", "Nam", "Nant",
        "Nap", "Natter", "Naze", "Neck", "Needle", "Nell", "Nep", "Nestle", "Net", "Nettle", "New",
        "Nex", "Nib", "Niggle", "Nip", "Nod", "Nop", "Nor", "Nos", "Not", "Notch", "Nub", "Nuf",
        "Num", "Nut", "Nye", "Ob", "Ock", "Odd", "Off", "Og", "Op", "Or", "Orme", "Os", "Ot",
        "Over", "Ox", "Oz", "Pack", "Pad", "Par", "Patch", "Pax", "Pebble", "Peck", "Ped", "Pell",
        "Pem", "Pen", "Pendle", "Per", "Pes", "Pet", "Peven", "Pew", "Pey", "Pib", "Pickle",
        "Piddle", "Pig", "Pill", "Pim", "Pin", "Pinder", "Pip", "Pir", "Pis", "Pit", "Pitch",
        "Pock", "Pod", "Poe", "Poll", "Ponder", "Poor", "Por", "Port", "Pos", "Pow", "Pub",
        "Puckle", "Pud", "Puddle", "Pup", "Pur", "Pus", "Put", "Rab", "Rad", "Raff", "Rag", "Rams",
        "Ran", "Ratch", "Rattle", "Raw", "Ray", "Reb", "Red", "Reg", "Rem", "Ren", "Rep", "Ret",
        "Rew", "Rid", "Rod", "Roll", "Rom", "Ros", "Row", "Roy", "Ruck", "Rud", "Ruf", "Rug",
        "Runny", "Rup", "Russel", "Rut", "Sack", "Saddle", "Sal", "Sale", "Sand", "Sar", "Sax",
        "Scaf", "Scal", "Scar", "Scone", "Seb", "Sedge", "Seg", "Sell", "Sen", "Sett", "Sew",
        "Sex", "Shel", "Shep", "Sher", "Shet", "Shin", "Ship", "Shop", "Sib", "Sid", "Skeg",
        "Skel", "Sod", "Somer", "Sonn", "Sop", "Spey", "Staf", "Stam", "Stan", "Stert", "Stig",
        "Stil", "Stimp", "Stirch", "Stort", "Streat", "Stud", "Sud", "Sug", "Sun", "Sut", "Swab",
        "Swar", "Sweet", "Tack", "Tad", "Taff", "Tam", "Tap", "Tar", "Tat", "Taver", "Taw",
        "Tedding", "Tel", "Tet", "Tetch", "Tewkes", "Thatch", "Tib", "Tils", "Tinder", "Tip",
        "Titch", "Tod", "Top", "Tor", "Tow", "Tox", "Tun", "Udder", "Uff", "Ulles", "Under", "Ux",
        "Wad", "Wall", "Wan", "Wapp", "War", "Wat", "Welling", "Web", "Wen", "Wetch", "Wex",
        "Whit", "Wil", "Win", "Witt", "Wom", "Wood", "Woot", "Wrex", "Wrought", "Wy", "Yard"};

    private static final String[] TOWN_SUFFIXES = {
        "age", "barrow", "beck", "beth", "borough", "bottom", "bourne", "bridge", "brook",
        "brough", "burgh", "bury", "carden", "caster", "castle", "cester", "chester", "church",
        "cliff", "combe", "cot", "cote", "cott", "dale", "den", "don", "field", "fold", "font",
        "ford", "forest", "forth", "garth", "gate", "grove", "hall", "ham", "hampton", "haven",
        "head", "hill", "holm", "holme", "holt", "hull", "hurst", "hythe", "kirk", "lade", "ley",
        "leigh", "lock", "low", "mere", "mill", "minster", "mond", "moor", "more", "mouth", "nall",
        "ness", "nor", "pool", "port", "puddle", "sell", "sett", "sey", "shaw", "stead", "ster",
        "side", "son", "stable", "stock", "stoke", "stone", "stow", "tham", "thorpe", "throp",
        "thwaite", "ton", "ward", "water", "way", "well", "wich", "wick", "wood", "worth", "wych"};


    private long lastTime = System.currentTimeMillis();
    private double errorProb = SCREW_UP_P;
    private Rand rand;

    private List<String> townNames = new ArrayList<>();
    private List<Integer> townPopulations = new ArrayList<>();
    private List<Long> railwayNone = new ArrayList<>();
    private List<Long> railwaySingle = new ArrayList<>();
    private List<Long> railwayDual = new ArrayList<>();

    @SuppressWarnings("PMD.LooseCoupling")  // We call LinkedList.poll(), which List doesn't have.
    private LinkedList<String> messages = new LinkedList<>();

    public TownsInput(long seed)
    {
        rand = new Rand(seed);
    }

    public TownsInput()
    {
        rand = new Rand();
    }

    public void setErrorProbability(double errorProb)
    {
        if(errorProb < 0.0 || 0.5 < errorProb)
        {
            throw new IllegalArgumentException(
                "The error probability is limited to 0-0.5. (It is applied repeatedly, and hence values approaching 1 would create a real mess.)");
        }
        this.errorProb = errorProb;
    }

    private String railwayMsg(long townsCode, String msgType)
    {
        return rand.maybeReversed(
            0.5,
            (int)(townsCode & 0xffffffffL),
            (int)(townsCode >> 32),
            (t1, t2) -> String.format(
                "%s %s %s", msgType, townNames.get(t1), townNames.get(t2)));
    }

    /**
     * Retrieves the next input message, for the bike shop simulation. This method generates these
     * messages randomly as needed.
     */
    public String nextMessage()
    {
        long thisTime = System.currentTimeMillis();
        for(long t = lastTime + 999L; t < thisTime; t += 1000L)
        {
            var newMessages = new ArrayList<String>();
            while(rand.event(RAILWAY_DUP_P) && !railwaySingle.isEmpty())
            {
                long r = rand.removeFrom(railwaySingle);
                railwayDual.add(r);
                newMessages.add(railwayMsg(r, "railway-duplication"));
            }
            while(rand.event(RAILWAY_CONSTR_P) && !railwayNone.isEmpty())
            {
                long r = rand.removeFrom(railwayNone);
                railwaySingle.add(r);
                newMessages.add(railwayMsg(r, "railway-construction"));
            }
            var nTowns = townNames.size();
            for(int i = 0; i < nTowns; i++)
            {
                if(rand.event(TOWN_POPULATION_P))
                {
                    var pop = (int)Math.ceil(townPopulations.get(i) * rand.randomDouble(TOWN_POP_CHANGE));
                    townPopulations.set(i, pop);
                    newMessages.add(String.format(
                        "town-population %s %d",
                        townNames.get(i),
                        townPopulations.get(i)));
                }
            }
            while(nTowns < 2 || rand.event(TOWN_FOUNDING_P))
            {
                var name = rand.nonRepeating(() ->
                    rand.choice(TOWN_PREWORD_P, () -> rand.from(TOWN_PREWORDS) + "_", () -> "")
                    + rand.from(TOWN_PREFIXES)
                    + rand.from(TOWN_SUFFIXES)).orElse(null);
                if(name == null) { break; }

                for(long i = 0; i < nTowns; i++)
                {
                    railwayNone.add(nTowns | (i << 32));
                }

                var pop = rand.randomInt(TOWN_INITIAL_POP);
                townNames.add(name);
                townPopulations.add(pop);
                newMessages.add(String.format("town-founding %s %d", name, pop));
                nTowns++;
            }

            while(rand.event(errorProb))
            {
                var parts = new ArrayList<String>();
                if(rand.event(0.5))
                {
                    parts.add(rand.choice("town-founding",
                                          "town-population",
                                          "railway-construction",
                                          "railway-duplication"));
                }
                if(rand.event(0.5))
                {
                    parts.add(rand.string(SCREW_UP_STRING_LEN, Rand.ALPHA_NUM));
                }
                if(rand.event(0.5))
                {
                    parts.add(rand.from(townNames));
                }
                if(rand.event(0.5))
                {
                    parts.add(rand.from(townNames));
                }
                if(rand.event(0.5))
                {
                    parts.add(String.valueOf(rand.randomInt(SCREW_UP_POP_RANGE)));
                }
                if(rand.event(0.5))
                {
                    parts.add(rand.string(SCREW_UP_STRING_LEN, Rand.PRINTABLE_ASCII));
                }
                newMessages.add(String.join(" ", parts));
            }
            rand.shuffle(newMessages);
            messages.addAll(newMessages);
        }
        lastTime = thisTime;
        return messages.poll();
    }
}



class Rand
{
    private static final int REPEAT_ATTEMPT_LIMIT = 100;
    private static final int REPEAT_CACHE_LIMIT = 1_000_000;


    public static CharRange range(char start, char end)
    {
        return new CharRange(start, end);
    }

    public static <T extends Comparable<T>> Range<T> range(T start, T end)
    {
        return new Range<>(start, end);
    }

    public static <N extends Number> Coord<N> coord(N a, N b)
    {
        return new Coord<>(a, b);
    }

    public static <N extends Number> Rect<N> rect(Coord<N> min, Coord<N> max)
    {
        return new Rect<>(min, max);
    }

    public static <N extends Number> Rect<N> rect(N minA, N minB, N maxA, N maxB)
    {
        return new Rect<>(new Coord<>(minA, minB), new Coord<>(maxA, maxB));
    }


    public static final CharSequence LOWER_ALPHA     = range('a', 'z');
    public static final CharSequence UPPER_ALPHA     = range('A', 'Z');
    public static final CharSequence DIGIT           = range('0', '1');
    public static final CharSequence ALPHA_NUM       = "" + LOWER_ALPHA + UPPER_ALPHA + DIGIT;
    public static final CharSequence PRINTABLE_ASCII = range(' ', '~');

    public static final Coord<Integer> INT_ORIGIN    = coord(0, 0);
    public static final Coord<Double>  DOUBLE_ORIGIN = coord(0.0, 0.0);

    private Random random;
    private Set<Object> prev = new HashSet<>();
    private boolean limitReported = false;

    public Rand(long seed)
    {
        this.random = new Random(seed);
    }

    public Rand()
    {
        this(System.currentTimeMillis());
    }

    public int randomInt(int exclusiveMax)
    {
        return random.nextInt(exclusiveMax);
    }

    public int randomInt(int min, int max)
    {
        return random.nextInt(max - min + 1) + min;
    }

    public int randomInt(Range<Integer> range)
    {
        return randomInt(range.getMin(), range.getMax());
    }

    public double randomDouble(double min, double max)
    {
        return (random.nextDouble() * (max - min)) + min;
    }

    public double randomDouble(Range<Double> range)
    {
        return randomDouble(range.getMin(), range.getMax());
    }

    public boolean event(double probability)
    {
        return random.nextDouble() < probability;
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    public final <T> T removeFrom(List<T>... lists)
    {
        return selectFrom((list, i) -> list.remove((int)i), List::size, lists);
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    public final <T> T from(List<T>... lists)
    {
        return selectFrom((list, i) -> list.get((int)i), List::size, lists);
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    public final <T> T from(T[]... arrays)
    {
        return selectFrom((array, i) -> array[i], array -> array.length, arrays);
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    public final <C,R> R selectFrom(BiFunction<C,Integer,R> getFn,
                                    Function<C,Integer> sizeFn,
                                    C... containers)
    {
        int count = 0;
        for(var container : containers)
        {
            count += sizeFn.apply(container);
        }
        if(count == 0)
        {
            throw new IllegalArgumentException(
                "At least one list argument must be non-empty");
        }

        int i = random.nextInt(count);
        for(var container : containers)
        {
            var size = sizeFn.apply(container);
            if(i < size)
            {
                return getFn.apply(container, i);
            }
            i -= size;
        }
        throw new AssertionError();
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    public final <T> T choice(T... array)
    {
        return array[random.nextInt(array.length)];
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    public final <T> T choice(Supplier<T>... generators)
    {
        return generators[random.nextInt(generators.length)].get();
    }

    public <T> T choice(double probability, Supplier<T> a, Supplier<T> b)
    {
        return event(probability) ? a.get() : b.get();
    }

    public String string(Range<Integer> lengthRange, Supplier<Character> chGenerator)
    {
        var sb = new StringBuilder();
        var len = randomInt(lengthRange);
        for(int i = 0; i < len; i++)
        {
            sb.append(chGenerator.get());
        }
        return sb.toString();
    }

    public String string(Range<Integer> lengthRange, CharSequence... chars)
    {
        int count = 0;
        for(var s : chars)
        {
            count += s.length();
        }
        if(count == 0)
        {
            throw new IllegalArgumentException(
                "At least one string argument must be non-empty");
        }

        int finalCount = count;
        return string(lengthRange, () ->
        {
            int i = random.nextInt(finalCount);
            for(var s : chars)
            {
                var size = s.length();
                if(i < size)
                {
                    return s.charAt(i);
                }
                i -= size;
            }
            throw new AssertionError();
        });
    }

    public char randomChar(CharSequence chars)
    {
        if(chars.length() == 0)
        {
            throw new IllegalArgumentException("String/CharSequence cannot be empty");
        }
        return chars.charAt(random.nextInt(chars.length()));
    }

    public Coord<Integer> randomIntCoord(int exclusiveMaxA, int exclusiveMaxB)
    {
        return new Coord<>(random.nextInt(exclusiveMaxA),
                           random.nextInt(exclusiveMaxB));
    }

    public Coord<Integer> randomIntCoord(Coord<Integer> exclusiveMax)
    {
        return randomIntCoord(exclusiveMax.getA(), exclusiveMax.getB());
    }

    public Coord<Integer> randomIntCoord(Range<Integer> rangeA, Range<Integer> rangeB)
    {
        return new Coord<>(randomInt(rangeA), randomInt(rangeB));
    }

    public Coord<Double> randomDoubleCoord(Range<Double> rangeA, Range<Double> rangeB)
    {
        return new Coord<>(randomDouble(rangeA), randomDouble(rangeB));
    }

    public Coord<Integer> randomIntCoord(Coord<Integer> min, Coord<Integer> max)
    {
        return new Coord<>(randomInt(min.getA(), max.getA()),
                           randomInt(min.getB(), max.getB()));
    }

    public Coord<Double> randomDoubleCoord(Coord<Double> min, Coord<Double> max)
    {
        return new Coord<>(randomDouble(min.getA(), max.getA()),
                           randomDouble(min.getB(), max.getB()));
    }

    public Coord<Integer> randomIntCoord(Rect<Integer> coordRange)
    {
        return randomIntCoord(coordRange.getMin(), coordRange.getMax());
    }

    public Coord<Double> randomDoubleCoord(Rect<Double> coordRange)
    {
        return randomDoubleCoord(coordRange.getMin(), coordRange.getMax());
    }

    public <T> Optional<T> nonRepeating(Supplier<T> generator)
    {
        int attempts = 0;
        T obj;
        do
        {
            attempts++;
            obj = generator.get();
        }
        while(prev.contains(obj) && attempts < REPEAT_ATTEMPT_LIMIT);

        if(prev.contains(obj))
        {
            return Optional.empty();
        }
        else if(prev.size() >= REPEAT_CACHE_LIMIT)
        {
            if(!limitReported)
            {
                System.err.printf(
                    "[Rand warning] Cache size at limit; cannot guarantee uniqueness now\n");
                limitReported = true;
            }
        }
        else
        {
            prev.add(obj);
        }
        return Optional.of(obj);
    }

    public <T> List<T> list(Range<Integer> lengthRange, Supplier<T> generator)
    {
        var theList = new ArrayList<T>();
        var len = randomInt(lengthRange);
        for(int i = 0; i < len; i++)
        {
            theList.add(generator.get());
        }
        return theList;
    }

    public <T,R> R maybeReversed(double reverseProb, T a, T b, BiFunction<T,T,R> c)
    {
        if(random.nextDouble() < reverseProb)
        {
            return c.apply(a, b);
        }
        else
        {
            return c.apply(b, a);
        }
    }

    public void shuffle(List<?> list)
    {
        Collections.shuffle(list, random);
    }
}


class Pair<T>
{
    protected final T a;
    protected final T b;
    public Pair(T a, T b)
    {
        this.a = a;
        this.b = b;
    }
    public T getA() { return a; }
    public T getB() { return b; }

    @Override
    public int hashCode()
    {
        return Objects.hash(a, b);
    }

    @Override
    public boolean equals(Object other)
    {
        return (other instanceof Pair) && a.equals(((Pair)other).a) && b.equals(((Pair)other).b);
    }
}

class Coord<N extends Number> extends Pair<N>
{
    public Coord(N a, N b)
    {
        super(a, b);
    }

    @Override
    public String toString()
    {
        return String.format("(%s,%s)", a, b);
    }

    public Rect<N> to(Coord<N> max)
    {
        return new Rect<>(this, max);
    }
}

class Rect<N extends Number> extends Pair<Coord<N>>
{
    public Rect(Coord<N> min, Coord<N> max)
    {
        super(min, max);
    }
    public Coord<N> getMin() { return a; }
    public Coord<N> getMax() { return b; }
}

class Range<T extends Comparable<T>> extends Pair<T>
{
    public Range(T a, T b)
    {
        super(a, b);
        if(a.compareTo(b) > 0)
        {
            throw new IllegalArgumentException(String.format(
                "First argument '%s' must not be greater than the second '%s'",
                a, b));
        }
    }
    public T getMin() { return a; }
    public T getMax() { return b; }
}

class CharRange extends Range<Character> implements CharSequence
{
    public CharRange(char start, char end)
    {
        super(start, end);
    }

    @Override
    public char charAt(int i)
    {
        if(i < 0 || i > (b - a))
        {
            throw new IndexOutOfBoundsException(String.format(
                "Cannot access index %d in CharRange \"%s\"", i, this));
        }
        return (char)(a + i);
    }

    @Override
    public int length()
    {
        return b - a + 1;
    }

    @Override
    public String toString()
    {
        var sb = new StringBuilder();
        for(char ch = a; ch <= b; ch++)
        {
            sb.append(ch);
        }
        return sb.toString();
    }

    @Override
    public CharSequence subSequence(int start, int end)
    {
        return toString().subSequence(start, end);
    }
}